package indi.mat.work.login.ui.login;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import indi.mat.work.login.R;
import indi.mat.work.login.ToolBarInfoViewModel;
import indi.mat.work.login.attr.ToolBarInfo;
import indi.mat.work.login.databinding.FragmentLoginFailedBinding;


public class LoginFailedFragment extends Fragment {

    private FragmentLoginFailedBinding binding;
    private ToolBarInfoViewModel toolBarInfoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_failed, container, false);
        toolBarInfoViewModel = new ViewModelProvider(getActivity()).get(ToolBarInfoViewModel.class);
        View root = binding.getRoot();
        ToolBarInfo toolBarInfo = toolBarInfoViewModel.getTitle().getValue();
        toolBarInfo.setTitle("Login Failed");
        toolBarInfoViewModel.setTitle(toolBarInfo);
        toolBarInfoViewModel.setIsVisible(true);
        System.out.println( "Home  F F :" + toolBarInfoViewModel);
        return root;
    }
}