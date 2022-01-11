package indi.mat.work.android.net.base;



import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import indi.mat.work.android.net.config.ApiCode;
import indi.mat.work.android.utilities.LogUtil;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Callback;

public abstract class BaseRemoteDataSource<T> {

    private CompositeDisposable compositeDisposable;

    public BaseRemoteDataSource(){
        compositeDisposable = new CompositeDisposable();
    }

    protected abstract T getService();

    protected T getService(Class<T> cls) {
        return RetrofitManagement.getInstance().getService(cls);
    }

    protected <R> void execute(Observable observable, Callback<R> callback){
        Disposable disposable = (Disposable) observable.throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(loadingTransformer())
                .subscribeWith(new BaseSubscriber(callback));

        compositeDisposable.add(disposable);
    }

    protected <R> void executeWithoutAnimation(Observable observable, Callback<R> callback){
        Disposable disposable = (Disposable) observable.throttleFirst(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new BaseSubscriber(callback));

        compositeDisposable.add(disposable);
    }

    private  ObservableTransformer loadingTransformer(){
        return observable -> observable.subscribeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(x -> showLoading())
                .doFinally(() -> dismissLoading());
    }

    private void showLoading() {
        NetStatusModel.getInstance().startLoading();
    }

    private void dismissLoading() {
        NetStatusModel.getInstance().dismissLoading();
    }

    public void dispose() {
        if(!compositeDisposable.isDisposed()){
            LogUtil.d("DISPOSE", this.getClass().getName());
            compositeDisposable.dispose();
        }
    }


    public boolean isDispose(){
        return compositeDisposable.isDisposed();
    }


    protected Map<String, String> objectToMap(Object object) {
        Map<String, String> map = new HashMap<>();
        Class cls = object.getClass();
        for (Field field : cls.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object val = null;
            try {
                val = field.get(object);
            } catch (IllegalAccessException e) {
                throw new BaseException(ApiCode.DATA_CONVERT_ERROR);
            }
            if ("null".equals(String.valueOf(val))) continue;
            map.put(fieldName, String.valueOf(val));
        }

        return map;
    }
}
