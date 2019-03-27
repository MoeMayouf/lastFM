package com.test.lastfm.domain.di;


import com.test.lastfm.domain.di.albumdetails.di.AlbumDetailsActivityModule;
import com.test.lastfm.domain.di.albumdetails.di.AlbumDetailsScope;
import com.test.lastfm.domain.di.albumresults.di.HomeActivityModule;
import com.test.lastfm.domain.di.albumresults.di.HomeScope;
import com.test.lastfm.presentation.albumdetails.AlbumDetailsActivity;
import com.test.lastfm.presentation.home.HomeActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    @HomeScope
    abstract HomeActivity homeActivity();

    @ContributesAndroidInjector(modules = AlbumDetailsActivityModule.class)
    @AlbumDetailsScope
    abstract AlbumDetailsActivity albumDetailsActivity();
}
