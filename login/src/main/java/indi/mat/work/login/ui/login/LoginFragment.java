package indi.mat.work.login.ui.login;

import android.content.Context;
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
        System.out.println("LoginFragment : onCreate");
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
                NavDirections action = LoginFragmentDirections.actionLoginFragmentToHomeNavGraph();
                Navigation.findNavController(view).navigate(action);
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
        System.out.println("LoginFragment : onCreateView");

        return root;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        toolBarInfoViewModel.setIsVisible(true);
        ToolBarInfo toolBarInfo = toolBarInfoViewModel.getTitle().getValue();
        toolBarInfo.setTitle("Login Main");
        toolBarInfoViewModel.setTitle(toolBarInfo);
        System.out.println("LoginFragment : onViewCreated");
    }


    @Override
    public void onAttach(@NonNull Context context) {
        System.out.println("LoginFragment : onAttach");
        super.onAttach(context);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        System.out.println("LoginFragment : onDestroyView");
    }


    @Override
    public void onStart() {
        super.onStart();
        System.out.println("LoginFragment : onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("LoginFragment : onResume");
    }


    @Override
    public void onPause() {
        super.onPause();
        System.out.println("LoginFragment : onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        System.out.println("LoginFragment : onStop");
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        System.out.println("LoginFragment : onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        System.out.println("LoginFragment : onDetach");
    }

    private boolean isPasswordValid(@Nullable Editable text) {
        return text != null && text.length() >= 1;
    }

    private boolean isUsernameValid(@Nullable Editable text) {
        return text != null && text.length() >= 1;
    }
}