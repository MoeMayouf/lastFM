package com.test.lastfm.presentation.albumdetails;


import com.test.lastfm.data.repository.DataSource;
import com.test.lastfm.domain.di.Repository;
import com.test.lastfm.presentation.home.HomePresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AlbumDetailModule {

    @Singleton
    @Provides
    public AlbumDetailsPresenter provideAlbumDetailPresenter(@Repository DataSource repository) {
        return new AlbumDetailsPresenter(repository);
    }
}

