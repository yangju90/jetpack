package indi.mat.work.android.model.viewmodel;

import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.List;

import indi.mat.work.android.base.BaseViewModel;
import indi.mat.work.android.data.LoginRepository;
import indi.mat.work.android.model.base.TokenRefresh;
import indi.mat.work.android.model.bean.LoginResult;
import indi.mat.work.android.model.bean.User;
import indi.mat.work.android.model.bean.WareHouse;
import indi.mat.work.android.model.reponse.login.AccountUserResponseInfo;
import indi.mat.work.android.model.request.login.LoginRequestInfo;
import indi.mat.work.android.utilities.UserInfoStore;


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
//                    TokenRefresh tokenRefresh = accountUserResponseInfo.getData();
                    User user = UserInfoStore.getUser();
//                    user.setToken(tokenRefresh.getToken());
//                    user.setRefreshToken(tokenRefresh.getRefreshToken());
                    user.setUserID(username.getValue());
                    user.setUrl("https://www.hao123.com/");
                    List<String> whList = new ArrayList<>();
                    whList.add("25");
                    whList.add("30");
                    user.setWhList(whList);
                    loginResult.setStatus(true);
                    loginResult.setWareHouseList(convertWareHouse(user.getWhList()));
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

    private List<WareHouse> convertWareHouse(List<String> whList){
        List<WareHouse> ans = new ArrayList<>();
        for(int i = 1; i<= whList.size(); i++){
            ans.add(new WareHouse(i, whList.get(i - 1) + "号仓库", whList.get(i -1)));
        }
        return ans;
    }
}
