package indi.mat.work.android;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import indi.mat.work.android.base.BaseActivity;
import indi.mat.work.android.base.LViewModelProviders;
import indi.mat.work.android.databinding.ActivityMainBinding;
import indi.mat.work.android.model.viewmodel.ToolBarInfoViewModel;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private ToolBarInfoViewModel toolBarInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        toolBarInfoViewModel = LViewModelProviders.of(this, ToolBarInfoViewModel.class);
        binding.setToolBarInfoViewModel(toolBarInfoViewModel);
        binding.setLifecycleOwner(this);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.fragmentContainerView.getId());
        NavController navController = navHostFragment.getNavController();


        AppBarConfiguration appBarConfiguration =
                new AppBarConfiguration.Builder(R.id.homeFragment).build();

        NavigationUI.setupWithNavController(binding.appBar, navController, appBarConfiguration);

        Glide.with(this).load("https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic3.zhimg.com%2F50%2Fv2-dd8b96d82d551fa0775f181d2d49e60a_hd.jpg&refer=http%3A%2F%2Fpic3.zhimg.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1644771482&t=2b59fb0db9e5c5e43b96e36eaf9bc974").circleCrop().into(binding.usrIcon);

    }

    protected void setObserver() {
        super.setObserver();
        toolBarInfoViewModel.getIsVisible().observe(this, (Boolean flag) -> {
            int visible = flag ? View.VISIBLE : View.GONE;
            binding.appBar.setVisibility(visible);
        });
    }

    @Override
    public void logout(){
        NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home_nav_graph, true).build();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.fragmentContainerView.getId());
        navHostFragment.getNavController().navigate(R.id.loginFragment, null, navOptions);
    }
}
