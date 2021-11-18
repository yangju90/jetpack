package indi.mat.work.login.ui.login;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indi.mat.work.login.R;
import indi.mat.work.login.MainActivityViewModel;
import indi.mat.work.login.attr.ToolBarInfo;
import indi.mat.work.login.databinding.FragmentLoginBinding;
import indi.mat.work.login.ui.home.HomeFragment;

public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private MainActivityViewModel viewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login, container, false);
        View root = binding.getRoot();
        viewModel = new ViewModelProvider(getActivity()).get(MainActivityViewModel.class);
        binding.setLifecycleOwner(getActivity());
        viewModel.setIsVisible(false);
        ToolBarInfo toolBarInfo = new ToolBarInfo();
        toolBarInfo.setTitle("Home");
        viewModel.getTitle().setValue(toolBarInfo);

        setListener();

        return root;
    }

    public void setListener(){
        binding.nextButton.setOnClickListener((View view) -> {
            boolean flag = true;
            if (!isUsernameValid(binding.usernameEditText.getText())) {
                binding.usernameTextHint.setError(getString(R.string.login_username_error));
                flag = false;
            } else {
                binding.usernameTextHint.setError(null);
            }

            if (!isPasswordValid(binding.passwordEditText.getText())) {
                binding.passwordTextHint.setError(getString(R.string.login_password_error));
                flag = false;
            } else {
                binding.passwordTextHint.setError(null);
            }


            if (flag) {
                NavController navController =  Navigation.findNavController(view);
                navController.navigate(R.id.action_loginFragment_to_homeFragment);
            }
        });


        binding.usernameEditText.setOnKeyListener((View view, int i, KeyEvent keyEvent) -> {
            if (isUsernameValid(binding.usernameEditText.getText())) {
                binding.usernameTextHint.setError(null);
            }
            return false;
        });


        binding.passwordEditText.setOnKeyListener((View view, int i, KeyEvent keyEvent) -> {
            if (isUsernameValid(binding.passwordEditText.getText())) {
                binding.passwordTextHint.setError(null);
            }
            return false;
        });
    }


    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 1;
    }

    private boolean isUsernameValid(@Nullable Editable text) {
        return text != null && text.length() >= 1;
    }
}