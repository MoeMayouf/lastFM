package com.test.lastfm.domain.di.albumresults.di;

import com.test.lastfm.presentation.home.HomeActivity;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class HomeActivityModule {
    @HomeScope
    @Binds
    abstract HomeActivity provideHomeActivity(HomeActivity homeActivity);
}
