package com.test.lastfm.data.service;

import com.test.lastfm.data.model.albumDetails.AlbumDetails;
import com.test.lastfm.data.model.albumResults.AlbumResultsResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFMService {

    @GET(".")
    Observable<AlbumResultsResponse> getAlbumResults(@Query("method") String methodName,
                                                     @Query("album") String albumName,
                                                     @Query("limit") int limit,
                                                     @Query("page") int page,
                                                     @Query("format") String responseFormat);


    @GET(".")
    Observable<AlbumDetails> getAlbumDetails(@Query("method") String methodName,
                                       @Query("album") String albumName,
                                       @Query("artist") String albumArtist,
                                       @Query("format") String responseFormat);
}
