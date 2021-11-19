package indi.mat.work.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;
import android.view.View;

import indi.mat.work.login.databinding.ActivityHomeBinding;


public class HomeActivity extends AppCompatActivity {

    private ActivityHomeBinding binding;
    private ToolBarInfoViewModel toolBarInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);

        toolBarInfoViewModel = new ViewModelProvider(this).get(ToolBarInfoViewModel.class);
        binding.setToolBarInfoViewModel(toolBarInfoViewModel);

        setObserver();
        setSupportActionBar(binding.appBar1);

        NavHostFragment navHostFragment =
                (NavHostFragment) getSupportFragmentManager().findFragmentById(binding.homeFragmentContainerView.getId());
        NavController navController = navHostFragment.getNavController();
    }


    private void setObserver() {
        toolBarInfoViewModel.getIsVisible().observe(this, (Boolean flag) -> {
            int visible = flag ? View.VISIBLE : View.GONE;
            binding.appBar1.setVisibility(visible);
        });
    }




}