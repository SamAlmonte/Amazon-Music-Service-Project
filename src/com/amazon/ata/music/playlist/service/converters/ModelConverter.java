package com.amazon.ata.music.playlist.service.converters;

import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;

import java.util.ArrayList;
import java.util.List;

public class ModelConverter {
    /**
     * Converts a provided {@link Playlist} into a {@link PlaylistModel} representation.
     * @param playlist the playlist to convert
     * @return the converted playlist
     */
    public PlaylistModel toPlaylistModel(Playlist playlist) {
        //List<String> aList = new ArrayList<>(playlist.getTags());
        if(playlist.getTags() == null)
            playlist.setTags(new ArrayList<>());
        if(playlist.getSongList() == null)
            playlist.setSongList(new ArrayList<>());
        if(playlist.getSongCount() == null)
            playlist.setSongCount(0);

        return PlaylistModel.builder()
            .withId(playlist.getId()).withName(playlist.getName()).withCustomerId(playlist.getCustomerId()).withSongCount(playlist.getSongCount()).withTags(playlist.getTags())
            .build();
    }
}
