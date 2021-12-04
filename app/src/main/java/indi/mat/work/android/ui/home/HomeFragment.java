package com.newegg.logistics.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.newegg.logistics.R;
import com.newegg.logistics.base.BaseFragment;
import com.newegg.logistics.base.LViewModelProviders;
import com.newegg.logistics.databinding.FragmentHomeBinding;
import com.newegg.logistics.behavior_collect.BehaviorSDK;
import com.newegg.logistics.net.base.NetStatusModel;


public class HomeFragment extends BaseFragment {

    private FragmentHomeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        toolBarInfo.setIsVisible(true);

        return root;
    }

    @Override
    public void onStart() {
        super.onStart();
        //每次homePage打开时检查上传行为收集日志
        String userName = "Jayson";
        BehaviorSDK.getInstance().checkPush(userName);

    }


    @Override
    protected void setListener() {
        super.setListener();
        binding.button.setOnClickListener((View v) -> {
            NetStatusModel.getInstance().loginOut();
        }) ;
    }
}