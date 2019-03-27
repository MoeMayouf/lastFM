package com.test.lastfm.domain;


import com.test.lastfm.data.model.albumDetails.AlbumDetails;
import com.test.lastfm.data.model.albumResults.AlbumResultsResponse;
import com.test.lastfm.data.repository.DataSource;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LastFMRepository implements DataSource {

    private final DataSource dataSource;

    public LastFMRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Observable<AlbumResultsResponse> getAlbums(String albumName, int page)  {
        return dataSource.getAlbums(albumName, page).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    @Override
    public Observable<AlbumDetails> getAlbumDetails(String albumName, String artistName) {
        return dataSource.getAlbumDetails(albumName, artistName).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
