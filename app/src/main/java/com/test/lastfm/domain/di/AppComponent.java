package com.test.lastfm.domain.di;

import android.app.Application;

import com.test.lastfm.common.LastFMClient;
import com.test.lastfm.presentation.albumdetails.AlbumDetailModule;
import com.test.lastfm.presentation.home.HomeModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

@Component(modules = {AndroidInjectionModule.class, BuildersModule.class,
        NetworkModule.class, RepositoryModule.class, HomeModule.class, AlbumDetailModule.class})
@Singleton
public interface AppComponent {
    void inject(LastFMClient lastFMClient);

    @Component.Builder
    interface Builder {
        AppComponent build();

        @BindsInstance
        Builder application(Application application);
    }
}
