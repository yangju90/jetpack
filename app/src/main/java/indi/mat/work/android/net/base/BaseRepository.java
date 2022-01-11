package indi.mat.work.android.net.base;

public class BaseRepository<T> {
    protected T remoteDataSource;

    public CallbackFactory callbackFactory;

    public BaseRepository(T remoteDataSource){
        this.remoteDataSource = remoteDataSource;
        this.callbackFactory = new CallbackFactory();
    }

    public void dispose(){
        if(remoteDataSource != null) ((BaseRemoteDataSource)remoteDataSource).dispose();
    }
}
