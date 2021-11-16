package indi.mat.work.login.ui.home;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indi.mat.work.login.MainActivityViewModel;
import indi.mat.work.login.NavigationHost;
import indi.mat.work.login.R;
import indi.mat.work.login.attr.ToolBarInfo;
import indi.mat.work.login.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;

    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home ,container, false);
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        View root = binding.getRoot();
        binding.setMainActivityViewModel(viewModel);
        binding.setLifecycleOwner(getActivity());
        viewModel.setIsVisible(true);
        ToolBarInfo toolBarInfo = new ToolBarInfo();
        toolBarInfo.setTitle("Home");
        viewModel.getTitle().setValue(toolBarInfo);


        return root;
    }

}