package indi.mat.work.android.base;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModel;

public class BaseViewModel extends ViewModel {

    protected LifecycleOwner lifecycleOwner;

    public BaseViewModel() {
    }

    void setLifecycleOwner(LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

}
