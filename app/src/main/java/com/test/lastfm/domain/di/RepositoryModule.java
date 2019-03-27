package com.test.lastfm.domain.di;

import com.test.lastfm.data.repository.DataSource;
import com.test.lastfm.domain.LastFMRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Repository
    @Singleton
    DataSource provideLastFMRepository(@Remote DataSource dataSource) {
        return new LastFMRepository(dataSource);
    }
}
