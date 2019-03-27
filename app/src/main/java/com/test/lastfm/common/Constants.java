package com.test.lastfm.common;

public class Constants {

    public static final String API_KEY = "5c25a91fc08edae9a0c80e06241cb0a1";
    public static final String BASE_URL = "https://ws.audioscrobbler.com/2.0/";
    public static final String QUERY_ALBUM_SEARCH = "album.search";
    public static final String QUERY_ALBUM_GET_INFO = "album.getInfo";
    public static final String RESULT_FORMAT = "json";
    public static final int RESULTS_PER_PAGE = 30;
    public static final int CACHE_SIZE = 5 * 1024 * 1024; //5 MB
    public static final int API_TIMEOUT = 30;

    public static final String KEY_ALBUM_NAME = "album_name";
    public static final String KEY_ALBUM_ARTIST = "album_artist";
}
