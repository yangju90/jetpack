package com.newegg.logistics.base;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.navigation.Navigation;

import com.newegg.logistics.model.viewmodel.ToolBarInfoViewModel;

import com.newegg.logistics.behavior_collect.BehaviorSDK;

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
        BehaviorSDK.getInstance().collectCommonLifeEvents(getClass().getSimpleName()+"  start ");
    }

    @Override
    public void onStop() {
        super.onStop();
        BehaviorSDK.getInstance().collectCommonLifeEvents(getClass().getSimpleName()+"  stop ");
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
