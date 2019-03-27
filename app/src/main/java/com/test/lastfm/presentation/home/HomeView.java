package com.test.lastfm.presentation.home;

import com.test.lastfm.data.model.albumResults.Album;
import com.test.lastfm.presentation.base.MvpView;

import java.util.List;

public interface HomeView extends MvpView{
        void displayAlbums(List<Album> albums);
        void displayError(String errorMessage);
        void displayProgressbar();
        void displayToast(String message);
}
