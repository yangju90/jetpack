package com.newegg.logistics.net.base;

import com.newegg.logistics.base.BaseViewModel;

public class BaseRepository<T> {
    protected T remoteDataSource;

    public CallbackFactory callbackFactory;

    public BaseRepository(T remoteDataSource, BaseViewModel baseViewModel){
        this.remoteDataSource = remoteDataSource;
        this.callbackFactory = new CallbackFactory(baseViewModel);
    }

}
