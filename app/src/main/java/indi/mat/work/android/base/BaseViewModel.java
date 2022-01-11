package indi.mat.work.android.base;

import androidx.annotation.NonNull;
import androidx.lifecycle.DefaultLifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

import indi.mat.work.android.net.base.BaseRepository;

public class BaseViewModel extends ViewModel {

    protected LifecycleOwner lifecycleOwner;

    public BaseViewModel() {
    }

    public BaseRepository getRepository(){
        return null;
    }

    void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
        if(getRepository() != null) {
            lifecycleOwner.getLifecycle().addObserver(new DefaultLifecycleObserver() {
                @Override
                public void onStop(@NonNull LifecycleOwner owner) {
                    if(getRepository() != null) getRepository().dispose();
                }
            });
        }
    }

}
