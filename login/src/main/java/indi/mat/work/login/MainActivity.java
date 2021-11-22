package indi.mat.work.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.View;

import indi.mat.work.login.attr.ToolBarInfo;
import indi.mat.work.login.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private ToolBarInfoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(ToolBarInfoViewModel.class);
        binding.setToolBarInfoViewModel(viewModel);

        binding.setLifecycleOwner(this);

        ToolBarInfo toolBarInfo = new ToolBarInfo();
        toolBarInfo.setTitle("Home");
        viewModel.getTitle().setValue(toolBarInfo);
        setObserver();
        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.fragmentContainerView.getId());
        NavController navController = navHostFragment.getNavController();
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(R.id.homeFragment).build();
        NavigationUI.setupWithNavController(binding.appBar, navController, appBarConfiguration);
        System.out.println("MainActivity  :  OnCreate" );
    }

    private void setObserver() {
        viewModel.getIsVisible().observe(this, (Boolean flag) -> {
            int a = flag ? View.VISIBLE : View.GONE;
            binding.appBar.setVisibility(a);
        });
    }


    @Override
    protected void onStart() {
        System.out.println("MainActivity  :  onStart" );
        super.onStart();
    }

    @Override
    protected void onResume() {
        System.out.println("MainActivity  :  onResume" );
        super.onResume();
    }

    @Override
    protected void onPause() {
        System.out.println("MainActivity  :  onPause" );
        super.onPause();
    }


    @Override
    protected void onStop() {
        System.out.println("MainActivity  :  onStop" );
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        System.out.println("MainActivity  :  onDestroy" );
        super.onDestroy();
    }
}