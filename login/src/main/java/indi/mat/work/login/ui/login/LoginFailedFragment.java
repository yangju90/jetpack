package indi.mat.work.login.ui.login;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
        System.out.println("LoginFailedFragment : onCreate");
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
        System.out.println("LoginFailedFragment : onCreateView");
        return root;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        System.out.println("LoginFailedFragment : onViewCreated");
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("LoginFailedFragment : onDestroyView");
    }


    @Override
    public void onStart() {
        super.onStart();
        System.out.println("LoginFailedFragment : onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("LoginFailedFragment : onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        System.out.println("LoginFailedFragment : onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("LoginFailedFragment : onStop");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("LoginFailedFragment : onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("LoginFailedFragment : onDetach");
    }

    @Override
    public void onAttach(@NonNull Context context) {
        System.out.println("LoginFailedFragment : onAttach");
        super.onAttach(context);
    }
}