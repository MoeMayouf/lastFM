package com.test.lastfm.presentation.base;

public class BasePresenter<T extends MvpView> {
    private T mView;
    public void attach(T view){
        this.mView = view;
    }
    public void detach(){
        this.mView = null;
    }
    public boolean isViewAttached(){
        return this.mView!=null;
    }

    public T getView() {
        return mView;
    }

}
