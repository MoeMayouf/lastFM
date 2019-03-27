package com.test.lastfm.domain.di.albumdetails.di;


import com.test.lastfm.presentation.albumdetails.AlbumDetailsActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AlbumDetailsActivityModule {
    @AlbumDetailsScope
    @Binds
    abstract AlbumDetailsActivity provideAlbumDetailsActivity(AlbumDetailsActivity albumDetailsActivity);
}
