package com.test.lastfm.presentation.albumdetails;

import com.test.lastfm.data.model.albumDetails.AlbumDetails;
import com.test.lastfm.presentation.base.MvpView;

public interface AlbumDetailView extends MvpView {
    void displayProgressbar();

    void displayAlbumDetail(AlbumDetails albumDetails);

    void displayError(String errorMessage);
}
