package indi.mat.work.android.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indi.mat.work.android.base.BaseFragment;
import indi.mat.work.android.databinding.FragmentHomeBinding;
import indi.mat.work.android.model.bean.VersionInfo;
import indi.mat.work.android.net.base.NetStatusModel;
import indi.mat.work.android.utilities.UpdateManager;


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

        binding.update.setOnClickListener((View v) -> {
            UpdateManager updateManager = new UpdateManager(getActivity());
            VersionInfo version = new VersionInfo();
            version.setStatus(true);
            version.setVersionMsg("更新apk文件");
            version.setVersionLink("https://download.oracle.com/java/17/latest/jdk-17_linux-x64_bin.tar.gz");

            updateManager.checkUpdateInfo(version);
        });

    }
}