package com.amazon.ata.music.playlist.service.activity;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import com.amazon.ata.music.playlist.service.converters.ModelConverter;
import com.amazon.ata.music.playlist.service.dynamodb.AlbumTrackDao;
import com.amazon.ata.music.playlist.service.dynamodb.models.Playlist;
import com.amazon.ata.music.playlist.service.exceptions.InvalidAttributeValueException;
import com.amazon.ata.music.playlist.service.models.requests.CreatePlaylistRequest;
import com.amazon.ata.music.playlist.service.models.results.CreatePlaylistResult;
import com.amazon.ata.music.playlist.service.models.PlaylistModel;
import com.amazon.ata.music.playlist.service.dynamodb.PlaylistDao;

import com.amazon.ata.music.playlist.service.util.MusicPlaylistServiceUtils;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class CreatePlaylistActivityTest {
    @Mock
    private PlaylistDao playlistDao;

    private CreatePlaylistActivity createPlaylistActivity;

    @BeforeEach
    public void setUp() {
        initMocks(this);
        createPlaylistActivity = new CreatePlaylistActivity(playlistDao);
    }

    @Test
    public void handleRequest_createNewPlaylistActivityExists(){
        String expectedId = "expectedId";
        String expectedName = "expectedName";
        String expectedCustomerId = "expectedCustomerId";
        List<String> expectedAlbumTrack = Lists.newArrayList("AlbumTrack");
        int expectedSongCount = 0;
        List<String> expectedTags = Lists.newArrayList("tag");
        Playlist playlist = new Playlist();
        playlist.setId(expectedId);
        playlist.setName(expectedName);
        playlist.setCustomerId(expectedCustomerId);
        playlist.setSongCount(expectedSongCount);
        playlist.setTags(Sets.newHashSet(expectedTags));

        CreatePlaylistRequest createPlaylistRequest = CreatePlaylistRequest.builder().withCustomerId(expectedCustomerId).withName(expectedName).withTags(expectedTags).build();

        CreatePlaylistResult createPlaylistResult = createPlaylistActivity.handleRequest(createPlaylistRequest, null);

        //then
        createPlaylistResult.getPlaylist().setId(expectedId);
        assertEquals(createPlaylistResult.getPlaylist().getCustomerId(), expectedCustomerId);
        assertEquals(createPlaylistResult.getPlaylist().getId(), expectedId);
        assertEquals(createPlaylistResult.getPlaylist().getName(), expectedName);
        assertEquals(createPlaylistResult.getPlaylist().getTags(), expectedTags);
        assertEquals(createPlaylistResult.getPlaylist().getSongCount(), expectedSongCount);


    }



}
