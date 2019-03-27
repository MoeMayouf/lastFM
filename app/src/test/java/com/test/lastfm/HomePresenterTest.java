package com.test.lastfm;

import com.test.lastfm.data.model.AlbumResults;
import com.test.lastfm.data.model.albumResults.Album;
import com.test.lastfm.data.model.albumResults.AlbumResultsResponse;
import com.test.lastfm.data.model.albumResults.Albummatches;
import com.test.lastfm.data.model.albumResults.Results;
import com.test.lastfm.domain.LastFMRepository;
import com.test.lastfm.presentation.home.HomePresenter;
import com.test.lastfm.presentation.home.HomeView;

import org.hamcrest.core.AnyOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomePresenterTest {
    @Mock
    HomeView view;

    @Mock
    LastFMRepository dataSource;

    HomePresenter presenter;

    AlbumResultsResponse resultsResponse;
    List<Album> albums = new ArrayList<>();
    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        presenter = new HomePresenter(dataSource);
        presenter.attach(view);

        resultsResponse = new AlbumResultsResponse();
        Results results = new Results();
        results.setOpensearchItemsPerPage("10");
        results.setOpensearchTotalResults("100");
        results.setOpensearchStartIndex("1");
        Album album = new Album();
        albums.add(album);
        Albummatches albumMatches = new Albummatches();
        albumMatches.setAlbum(albums);
        results.setAlbummatches(albumMatches);
        resultsResponse.setResults(results);
    }
    @Test
    public void successFetch(){
        //When
        when(dataSource.getAlbums("hello",1)).thenReturn(Observable.just(resultsResponse));
        //Call
        presenter.getFreshData("hello");
        //Verify
        verify(view).displayProgressbar();
        verify(view).displayAlbums(albums);
    }

    @Test
    public void errorFetch(){
        //When
        String errorMessage = "My Exception";
        when(dataSource.getAlbums("hello",1)).thenReturn(Observable.error(new Throwable(errorMessage)));
        //Call
        presenter.getFreshData("hello");
        //Verify
        verify(view).displayProgressbar();
        verify(view).displayError(errorMessage);
    }

    @After
    public void tearDown(){
        presenter.detach();
    }

}
