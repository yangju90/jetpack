package indi.mat.work.android;

import android.os.Bundle;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

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
                new AppBarConfiguration.Builder(R.navigation.main_nav_graph).build();

        NavigationUI.setupWithNavController(binding.appBar, navController, appBarConfiguration);

        binding.appBarLayout.setVisibility(View.GONE);
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
