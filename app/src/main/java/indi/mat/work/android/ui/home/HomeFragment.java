package indi.mat.work.android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indi.mat.work.android.base.BaseFragment;
import indi.mat.work.android.databinding.FragmentHomeBinding;
import indi.mat.work.android.net.base.NetStatusModel;


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
    }


    @Override
    protected void setListener() {
        super.setListener();
        binding.button.setOnClickListener((View v) -> {
            NetStatusModel.getInstance().loginOut();
        }) ;
    }
}