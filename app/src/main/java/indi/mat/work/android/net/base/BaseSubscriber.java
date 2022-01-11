package indi.mat.work.android.net.base;

import androidx.annotation.NonNull;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import retrofit2.Callback;
import retrofit2.Response;

public class BaseSubscriber<T> extends DisposableObserver<T> {

    private Callback<T> callback;

    public BaseSubscriber(Callback<T> callback){
        this.callback = callback;
    }

    @Override
    public void onNext(@NonNull T t) {
        if(callback != null) callback.onResponse(null, (Response<T>) t);
    }

    @Override
    public void onError(@NonNull Throwable e) {
        if(callback != null) callback.onFailure(null, e);
    }

    @Override
    public void onComplete() {

    }
}
