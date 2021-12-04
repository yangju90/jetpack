package com.newegg.logistics.model.viewmodel;

import android.text.Editable;

import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.newegg.logistics.R;
import com.newegg.logistics.base.BaseViewModel;
import com.newegg.logistics.data.LoginRepository;
import com.newegg.logistics.model.base.TokenRefresh;
import com.newegg.logistics.model.bean.LoginResult;
import com.newegg.logistics.model.bean.User;
import com.newegg.logistics.model.reponse.login.AccountUserResponseInfo;
import com.newegg.logistics.model.request.login.LoginRequestInfo;
import com.newegg.logistics.utilities.ToastHolder;
import com.newegg.logistics.utilities.UserInfoStore;

public class LoginViewModel extends BaseViewModel {

    private LoginRepository loginRepository;

    private MutableLiveData<String> username;
    private MutableLiveData<LoginResult> loginResultLiveData;

    public LoginViewModel(){
        super();
        this.username = new MutableLiveData<>();
        this.loginResultLiveData = new MutableLiveData<>();
        LoginResult loginResult = new LoginResult();
        loginResult.setStatus(false);
        loginResult.setMessage("");
        this.getLoginResultLiveData().setValue(loginResult);
        this.loginRepository = LoginRepository.getInstance(this);
    }

    public MutableLiveData<String> getUsername() {
        return username;
    }

    public void setUsername(MutableLiveData<String> username) {
        this.username = username;
    }

    public MutableLiveData<LoginResult> getLoginResultLiveData() {
        return loginResultLiveData;
    }

    public void setLoginResultLiveData(MutableLiveData<LoginResult> loginResultLiveData) {
        this.loginResultLiveData = loginResultLiveData;
    }

    public void login(){
        LoginRequestInfo loginRequestInfo = new LoginRequestInfo();
        loginRequestInfo.setEmployeeID(username.getValue());
        LoginResult loginResult = new LoginResult();
        if(!isUsernameValid()){
            loginResult.setStatus(false);
            loginResult.setMessage("Username must not null.");
            loginResultLiveData.setValue(loginResult);
        }else {
            loginRepository.login(loginRequestInfo).observe(lifecycleOwner, (AccountUserResponseInfo accountUserResponseInfo) -> {
                if (accountUserResponseInfo.getStatus()) {
                    TokenRefresh tokenRefresh = accountUserResponseInfo.getData();
                    User user = UserInfoStore.getUser();
                    user.setToken(tokenRefresh.getToken());
                    user.setRefreshToken(tokenRefresh.getRefreshToken());
                    user.setUserID(username.getValue());
                    user.setUrl("https://www.hao123.com/");
                    loginResult.setStatus(true);
                } else {
                    loginResult.setStatus(false);
                    loginResult.setMessage(accountUserResponseInfo.getMessage());
                }
                loginResultLiveData.setValue(loginResult);
            });
        }

    }


    private boolean isUsernameValid() {
        return username.getValue() != null && username.getValue().length() > 0;
    }
}
