package indi.mat.work.android.net.base;

import indi.mat.work.android.base.BaseViewModel;

public class BaseRepository<T> {
    protected T remoteDataSource;

    public CallbackFactory callbackFactory;

    public BaseRepository(T remoteDataSource, BaseViewModel baseViewModel){
        this.remoteDataSource = remoteDataSource;
        this.callbackFactory = new CallbackFactory(baseViewModel);
    }

}
