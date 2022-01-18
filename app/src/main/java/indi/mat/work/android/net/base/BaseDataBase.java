package indi.mat.work.android.net.base;

import androidx.room.RoomDatabase;

import indi.mat.work.android.utilities.LogUtil;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public abstract class BaseDataBase extends RoomDatabase {

    private CompositeDisposable compositeDisposable = new CompositeDisposable();


    public <T> void addDisposable(Flowable<T> flowable, Consumer<T> consumer) {
        compositeDisposable.add(flowable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(consumer));
    }

    public <T> void addDisposable(Completable completable, Action action) {
        compositeDisposable.add(completable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(action));
    }

    public void dispose() {
        if(!compositeDisposable.isDisposed()){
            LogUtil.d("DISPOSE", this.getClass().getName());
            compositeDisposable.dispose();
        }
    }
}
