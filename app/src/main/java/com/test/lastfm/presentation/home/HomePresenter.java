package com.test.lastfm.presentation.home;


import com.test.lastfm.presentation.base.BasePresenter;
import com.test.lastfm.data.model.albumResults.AlbumResultsResponse;
import com.test.lastfm.data.repository.DataSource;

import io.reactivex.disposables.CompositeDisposable;

public class HomePresenter extends BasePresenter<HomeView> {

    private final CompositeDisposable compositeDisposable;

    private final DataSource lastFMRepository;

    private boolean isLoading;
    private boolean isLastPage;
    private int currentPage = 1;
    private String currentSearchQuery = "";

    public HomePresenter(DataSource lastFMRepository) {
        this.lastFMRepository = lastFMRepository;
        compositeDisposable = new CompositeDisposable();
    }


    public void getFreshData(String albumName){
        currentSearchQuery = albumName;
        getAlbums(currentSearchQuery, true);
    }
    void getAlbums(String albumName, boolean isNewQuery) {
        isLoading = true;
        if(isNewQuery){
            currentPage = 1;
            getView().displayProgressbar();
        }
        compositeDisposable.add(lastFMRepository.getAlbums(albumName, currentPage)
                .subscribe(this::handleSuccess, this::handleError)
        );
    }

    void loadMoreAlbums() {
        getAlbums(currentSearchQuery, false);
    }

    private void handleSuccess(AlbumResultsResponse albumResults) {
        isLoading = false;
        currentPage++;
        int startIndex = Integer.parseInt(albumResults.getResults().getOpensearchStartIndex());
        int itemPerPage = Integer.parseInt(albumResults.getResults().getOpensearchItemsPerPage());
        int totalResults = Integer.parseInt(albumResults.getResults().getOpensearchTotalResults());

        isLastPage = (startIndex + itemPerPage)
                >= totalResults;
        if(isViewAttached()){
            getView().displayAlbums(albumResults.getResults().getAlbummatches().getAlbum());
        }
    }

    private void handleError(Throwable throwable) {
        isLoading= false;
        if(isViewAttached()){
            if(currentPage==1) {
                getView().displayError(throwable.getLocalizedMessage());
            }else{
                getView().displayToast(throwable.getLocalizedMessage());
            }
        }
    }

    boolean isLoading() {
        return isLoading;
    }

    boolean isLastPage() {
        return isLastPage;
    }


}
