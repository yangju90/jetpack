package indi.mat.work.android.base;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

public class LViewModelProviders {
    public static <T extends BaseViewModel> T of(AppCompatActivity activity, Class<T> cls){
        T t = new ViewModelProvider(activity).get(cls);
        if(t.lifecycleOwner == null) t.setLifecycleOwner(activity);
        return t;
    }

    public static <T extends BaseViewModel> T of(FragmentActivity fragmentActivity, Class<T> cls){
        T t = new ViewModelProvider(fragmentActivity).get(cls);
        if(t.lifecycleOwner == null) t.setLifecycleOwner(fragmentActivity);
        return t;
    }


    public static <T extends BaseViewModel> T of(Fragment fragment, Class<T> cls){
        T t = new ViewModelProvider(fragment).get(cls);
        if(t.lifecycleOwner == null) t.setLifecycleOwner(fragment);
        return t;
    }

    public static <T extends BaseNavModel> T of(ViewModelStoreOwner viewModelStoreOwner, Class<T> cls) {
        T t = new ViewModelProvider(viewModelStoreOwner).get(cls);
        return t;
    }
}
