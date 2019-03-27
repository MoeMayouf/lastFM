package com.test.lastfm.presentation.albumdetails;

import com.test.lastfm.data.model.albumDetails.AlbumDetails;
import com.test.lastfm.data.repository.DataSource;
import com.test.lastfm.presentation.base.BasePresenter;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.Subject;

public class AlbumDetailsPresenter extends BasePresenter<AlbumDetailView> {

    private final CompositeDisposable compositeDisposable;
    private final DataSource lastFMRepository;

    public AlbumDetailsPresenter(DataSource lastFMRepository) {
        this.lastFMRepository = lastFMRepository;
        compositeDisposable = new CompositeDisposable();
    }

    void getAlbumDetails(String albumName, String albumArtist) {
        if(isViewAttached()){
            getView().displayProgressbar();
        }
        compositeDisposable.add(lastFMRepository.getAlbumDetails(albumName, albumArtist)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::handleSuccess, this::handleFailure));
    }

    private void handleSuccess(AlbumDetails albumDetails) {
        if(isViewAttached()){
            getView().displayAlbumDetail(albumDetails);
        }
    }

    private void handleFailure(Throwable throwable) {
        if(isViewAttached()){
            getView().displayError(throwable.getLocalizedMessage());
        }
    }

}
