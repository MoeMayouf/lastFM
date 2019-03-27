package com.test.lastfm.data.repository;

import com.test.lastfm.data.model.albumDetails.AlbumDetails;
import com.test.lastfm.data.model.albumResults.AlbumResultsResponse;

import io.reactivex.Observable;

public interface DataSource {

    Observable<AlbumResultsResponse> getAlbums(String albumName, int page) ;

    Observable<AlbumDetails> getAlbumDetails(String albumName, String artistName) ;
}
