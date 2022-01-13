package indi.mat.work.android.ui.login;

import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import indi.mat.work.android.R;
import indi.mat.work.android.base.BaseFragment;
import indi.mat.work.android.base.LViewModelProviders;
import indi.mat.work.android.databinding.FragmentLoginBinding;
import indi.mat.work.android.model.bean.LoginResult;
import indi.mat.work.android.model.viewmodel.LoginViewModel;
import indi.mat.work.android.utilities.KeyWordsUtil;
import indi.mat.work.android.utilities.UserInfoStore;
import indi.mat.work.android.widget.BottomDrawer;


public class LoginFragment extends BaseFragment {

    private FragmentLoginBinding binding;
    private LoginViewModel loginViewModel;
    private BottomDrawer bottomDrawer;


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

        bottomDrawer = new BottomDrawer(getActivity());
        binding.usernameEditText.requestFocus();
        binding.buttonLogin.setFocusable(false);
        KeyWordsUtil.closeKeyBoard(binding.usernameEditText, getActivity());

        return root;
    }


    @Override
    protected void setObserver() {
        super.setObserver();
        loginViewModel.getLoginResultLiveData().observe(this, (LoginResult loginResult) -> {
            if (loginResult.getStatus()) {
                bottomDrawer.show(loginResult.getWareHouseList());
            } else {
                binding.usernameTextHint.setError(loginResult.getMessage());
            }
        });
    }

    @Override
    public void setListener() {
        binding.usernameEditText.setOnKeyListener((View view, int i, KeyEvent keyEvent) -> {
            if(keyEvent != null && keyEvent.getKeyCode() == KeyEvent.KEYCODE_ENTER && keyEvent.getAction() == KeyEvent.ACTION_DOWN){
                loginViewModel.login();
            }
            if (isUserNameValid(loginViewModel.getUsername().getValue())) {
                binding.usernameTextHint.setError(null);
            }

            KeyWordsUtil.showSoftInputFromWindow(binding.usernameEditText);
            return false;
        });

        bottomDrawer.setOnChoiceWarehouseListener(warehouse -> {
            UserInfoStore.getUser().setCurrWh(warehouse.getNameNo());
            NavDirections action = LoginFragmentDirections.actionLoginFragmentToHomeNavGraph();
            Navigation.findNavController(binding.getRoot()).navigate(action);
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