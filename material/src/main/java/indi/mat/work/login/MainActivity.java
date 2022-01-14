package indi.mat.work.login;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavOptions;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

import indi.mat.work.login.base.BaseActivity;
import indi.mat.work.login.databinding.ActivityMainBinding;
import indi.mat.work.login.model.ToolBarInfoViewModel;

public class MainActivity extends BaseActivity {

    private ActivityMainBinding binding;
    private ToolBarInfoViewModel viewModel;
    private NavController navController;
    private AppBarConfiguration appBarConfiguration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ToolBarInfoViewModel.class);
        binding.setToolBarInfoViewModel(viewModel);
        binding.setLifecycleOwner(this);

        viewModel.getTitle().setValue("Login");

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.fragmentContainerView.getId());
        navController = navHostFragment.getNavController();
        appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment, R.id.productFragment, R.id.straggleProductFragment).setOpenableLayout(binding.drawerLayout).build();
        NavigationUI.setupWithNavController(binding.appBar, navController, appBarConfiguration);
        System.out.println("MainActivity  :  OnCreate");
    }

    @Override
    protected void setObserver() {
        viewModel.getIsVisible().observe(this, (Boolean flag) -> {
            int a = flag ? View.VISIBLE : View.GONE;
            binding.appBar.setVisibility(a);
        });

        viewModel.getMenuVisible().observe(this, (Boolean flag) -> {
            binding.appBar.getMenu().setGroupVisible(Menu.NONE, flag);
        });
    }

    @Override
    protected void setListener() {
        binding.appBar.setOnMenuItemClickListener((MenuItem item) -> {
            NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home_nav_graph, false).build();
            switch (item.getItemId()) {
                case R.id.home:
                    navController.navigate(R.id.homeFragment, null, navOptions);
                    break;
                case R.id.product:
                    navController.navigate(R.id.productFragment, null, navOptions);
                    break;
                case R.id.staggeredProduct:
                    navController.navigate(R.id.straggleProductFragment, null, navOptions);
                    break;
                default:
            }
            return false;
        });

        binding.navView.setNavigationItemSelectedListener((@NonNull MenuItem item) -> {
            NavOptions navOptions = new NavOptions.Builder().setPopUpTo(R.id.home_nav_graph, false).build();
            switch (item.getItemId()) {
                case R.id.home:
                    navController.navigate(R.id.homeFragment, null, navOptions);
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.product:
                    navController.navigate(R.id.productFragment, null, navOptions);
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                case R.id.staggeredProduct:
                    navController.navigate(R.id.straggleProductFragment, null, navOptions);
                    binding.drawerLayout.closeDrawer(GravityCompat.START);
                    break;
                default:
            }
            return false;
        });
    }


    @Override
    protected void onStart() {
        System.out.println("MainActivity  :  onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        System.out.println("MainActivity  :  onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("MainActivity  :  onPause");
        super.onPause();
    }


    @Override
    protected void onStop() {
        System.out.println("MainActivity  :  onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("MainActivity  :  onDestroy");
        super.onDestroy();
    }

    public static void start(Context context, String charId){
        Intent intent =new Intent();
        intent.putExtra("chatId", charId);
        intent.setClass(context, MainActivity.class);
        context.startActivity(intent);
    }
}