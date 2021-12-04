package com.newegg.logistics.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import com.newegg.logistics.R;
import com.newegg.logistics.base.BaseFragment;
import com.newegg.logistics.base.LViewModelProviders;
import com.newegg.logistics.databinding.FragmentLoginBinding;
import com.newegg.logistics.model.bean.LoginResult;
import com.newegg.logistics.model.bean.User;
import com.newegg.logistics.model.viewmodel.LoginViewModel;
import com.newegg.logistics.utilities.UserInfoStore;


public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        loginViewModel = LViewModelProviders.of(this, LoginViewModel.class);
        binding.setLoginViewModel(loginViewModel);
        binding.setLifecycleOwner(this);
        View root = binding.getRoot();
        toolBarInfo.setIsVisible(true);
        toolBarInfo.setTitle("Login Main");

        return root;
    }


    @Override
    protected void setObserver() {
        super.setObserver();
        loginViewModel.getLoginResultLiveData().observe(this, (LoginResult loginResult) -> {
            if(loginResult.getStatus()){
                NavDirections action = LoginFragmentDirections.actionLoginFragmentToHomeNavGraph();
                Navigation.findNavController(binding.getRoot()).navigate(action);
            }else{
                binding.usernameTextHint.setError(loginResult.getMessage());
            }
        });
    }

    @Override
    public void setListener() {
        binding.usernameEditText.setOnKeyListener((View view, int i, KeyEvent keyEvent) -> {
            if (isUserNameValid(loginViewModel.getUsername().getValue())) {
                binding.usernameTextHint.setError(null);
            }
            return false;
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private boolean isUserNameValid(String username) {
        if (username == null) {
            return false;
        }
        if (username.contains("@")) {
            return Patterns.EMAIL_ADDRESS.matcher(username).matches();
        } else {
            return !username.trim().isEmpty();
        }
    }


}