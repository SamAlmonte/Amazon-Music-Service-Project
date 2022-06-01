package com.amazon.ata.music.playlist.service.activity;

import com.amazon.ata.music.playlist.service.dynamodb.models.AlbumTrack;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.models.SongOrder;
import com.amazon.ata.music.playlist.service.models.requests.GetPlaylistSongsRequest;
import com.amazon.ata.music.playlist.service.models.results.GetPlaylistSongsResult;
import com.amazon.ata.music.playlist.service.models.SongModel;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of the GetPlaylistSongsActivity for the MusicPlaylistService's GetPlaylistSongs API.
 *
 * This API allows the customer to get the list of songs of a saved playlist.
 */
public class GetPlaylistSongsActivity implements RequestHandler<GetPlaylistSongsRequest, GetPlaylistSongsResult> {
    private final Logger log = LogManager.getLogger();
    private final PlaylistDao playlistDao;

    /**
     * Instantiates a new GetPlaylistSongsActivity object.
     *
     * @param playlistDao PlaylistDao to access the playlist table.
     */
    @Inject
    public GetPlaylistSongsActivity(PlaylistDao playlistDao) {
        this.playlistDao = playlistDao;
    }

    /**
     * This method handles the incoming request by retrieving the playlist from the database.
     * <p>
     * It then returns the playlist's song list.
     * <p>
     * If the playlist does not exist, this should throw a PlaylistNotFoundException.
     *
     * @param getPlaylistSongsRequest request object containing the playlist ID
     * @return getPlaylistSongsResult result object containing the playlist's list of API defined {@link SongModel}s
     */
    @Override
    public GetPlaylistSongsResult handleRequest(final GetPlaylistSongsRequest getPlaylistSongsRequest, Context context) {
        log.info("Received GetPlaylistSongsRequest {}", getPlaylistSongsRequest);
        //getPlaylistSongsRequest.getOrder().equals(SongOrder.SHUFFLED)
        Playlist playlist = playlistDao.getPlaylist(getPlaylistSongsRequest.getId());
        SongOrder songOrder = getPlaylistSongsRequest.getOrder();
        if(songOrder != null){
            if(songOrder.equals(SongOrder.DEFAULT)) {
                //donothingHere
            }
            else if (songOrder.equals(SongOrder.REVERSED)){
                List<AlbumTrack> songs = playlist.getSongList();
                Collections.reverse(songs);
                playlist.setSongList(songs);
            } else if (songOrder.equals(SongOrder.SHUFFLED)) {
                List<AlbumTrack> songs = playlist.getSongList();
                Collections.shuffle(songs);
                playlist.setSongList(songs);
            }
            else {
                throw new IllegalArgumentException("The song order is invalid.");
            }
        }
        List<SongModel> aList = new ArrayList<SongModel>();
        for (int i =0; i < playlist.getSongList().size(); i++) {
            aList.add(SongModel.builder().withTitle(playlist.getSongList().get(i).getSongTitle()).withAlbum(playlist.getSongList().get(i).getAlbumName()).withAsin(playlist.getSongList().get(i).getAsin()).withTrackNumber(playlist.getSongList().get(i).getTrackNumber()).build());
        }

        return GetPlaylistSongsResult.builder()
                .withSongList(aList)
                .build();
    }
}
