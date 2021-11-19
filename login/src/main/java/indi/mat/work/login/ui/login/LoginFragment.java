package indi.mat.work.login.ui.login;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import indi.mat.work.login.R;
import indi.mat.work.login.ToolBarInfoViewModel;
import indi.mat.work.login.attr.ToolBarInfo;
import indi.mat.work.login.databinding.FragmentLoginBinding;
import indi.mat.work.login.utilities.NormalEditTextOnKeyListener;


public class LoginFragment extends Fragment {

    private FragmentLoginBinding binding;

    private ToolBarInfoViewModel toolBarInfoViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        toolBarInfoViewModel = new ViewModelProvider(getActivity()).get(ToolBarInfoViewModel.class);


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
                NavDirections action = LoginFragmentDirections.actionLoginFragmentToHomeActivity();
                Navigation.findNavController(view).navigate(action);
                getActivity().finish();
            }else{
                NavDirections action = LoginFragmentDirections.actionLoginFragmentToLoginFailedFragment();
                Navigation.findNavController(view).navigate(action);
            }
        });


        binding.usernameEditText.setOnKeyListener(new NormalEditTextOnKeyListener() {
            @Override
            public void apply() {
                binding.usernameTextHint.setError(null);
            }
        });

        binding.passwordEditText.setOnKeyListener(new NormalEditTextOnKeyListener());

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolBarInfoViewModel.setIsVisible(true);
        ToolBarInfo toolBarInfo = toolBarInfoViewModel.getTitle().getValue();
        toolBarInfo.setTitle("Login Main");
        toolBarInfoViewModel.setTitle(toolBarInfo);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 1;
    }

    private boolean isUsernameValid(@Nullable Editable text) {
        return text != null && text.length() >= 1;
    }
}