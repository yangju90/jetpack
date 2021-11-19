package indi.mat.work.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

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
        setSupportActionBar(binding.appBar);
        setObserver();
    }

    private void setObserver() {
        viewModel.getIsVisible().observe(this, (Boolean flag) -> {
            int a = flag ? View.VISIBLE : View.GONE;
            binding.appBar.setVisibility(a);
        });
    }


}