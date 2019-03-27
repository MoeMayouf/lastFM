package com.test.lastfm.presentation.home;


import com.test.lastfm.data.repository.DataSource;
import com.test.lastfm.domain.di.Repository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class HomeModule {

    @Singleton
    @Provides
    public HomePresenter provideHomePresenter(@Repository DataSource repository) {
        return new HomePresenter(repository);
    }
}

