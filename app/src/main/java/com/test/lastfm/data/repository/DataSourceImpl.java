package com.test.lastfm.data.repository;


import com.test.lastfm.common.Constants;
import com.test.lastfm.data.model.albumDetails.AlbumDetails;
import com.test.lastfm.data.model.albumResults.AlbumResultsResponse;
import com.test.lastfm.data.service.LastFMService;

import io.reactivex.Observable;

public class DataSourceImpl implements DataSource {

    private final LastFMService lastFMService;

    public DataSourceImpl(LastFMService lastFMService) {
        this.lastFMService = lastFMService;
    }


    @Override
    public Observable<AlbumResultsResponse> getAlbums(String albumName, int page) {
        return
                lastFMService.getAlbumResults(Constants.QUERY_ALBUM_SEARCH,
                        albumName,
                        Constants.RESULTS_PER_PAGE,
                        page,
                        Constants.RESULT_FORMAT);
    }

    @Override
    public Observable<AlbumDetails> getAlbumDetails(String albumName, String artistName) {
        return
                lastFMService.getAlbumDetails(Constants.QUERY_ALBUM_GET_INFO,
                        albumName,
                        artistName,
                        Constants.RESULT_FORMAT);
    }
}
