package indi.mat.work.android.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import indi.mat.work.android.model.viewmodel.ToolBarInfoViewModel;

public class BaseFragment extends Fragment {

    protected ToolBarInfoViewModel toolBarInfo;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        toolBarInfo = new ViewModelProvider(getActivity()).get(ToolBarInfoViewModel.class);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setListener();
        setObserver();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    protected <T extends BaseNavModel> T createNavViewModel(@IdRes int navGraphId, Class<T> clazz){
        ViewModelStoreOwner viewModelStoreOwner = Navigation.findNavController(getParentFragment().getView()).getViewModelStoreOwner(navGraphId);
        return LViewModelProviders.of(viewModelStoreOwner, clazz);
    }

    protected void setListener() {

    }

    protected void setObserver() {

    }
}
