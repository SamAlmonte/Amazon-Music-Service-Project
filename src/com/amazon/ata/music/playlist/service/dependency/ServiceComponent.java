package com.amazon.ata.music.playlist.service.dependency;

import com.amazon.ata.music.playlist.service.activity.*;
import dagger.Component;

import javax.inject.Singleton;

@Component (modules = DaoModule.class)
@Singleton
public interface ServiceComponent {
    public AddSongToPlaylistActivity provideAddSongToPlaylistActivity();

    public CreatePlaylistActivity provideCreatePlaylistActivity();

    public GetPlaylistActivity provideGetPlaylistActivity();

    public GetPlaylistSongsActivity provideGetPlaylistSongsActivity();

    public UpdatePlaylistActivity provideUpdatePlaylistActivity();
}
