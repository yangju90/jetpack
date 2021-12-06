package indi.mat.work.android.net;

import java.io.IOException;

import indi.mat.work.android.model.reponse.login.AccountUserResponseInfo;
import indi.mat.work.android.model.request.login.LoginRequestInfo;
import indi.mat.work.android.net.base.BaseRemoteDataSource;
import indi.mat.work.android.net.service.LoginService;
import retrofit2.Callback;
import retrofit2.Response;

public class AccountUserDataSource extends BaseRemoteDataSource<LoginService> {

    @Override
    protected LoginService getService() {
        return getService(LoginService.class);
    }

    public Response<AccountUserResponseInfo> loginSync(LoginRequestInfo loginRequestInfo){
        try {
           return getService().accountUserLogin(loginRequestInfo).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void login(LoginRequestInfo loginRequestInfo, Callback<AccountUserResponseInfo> callback){
        getService().accountUserLogin(loginRequestInfo).enqueue(callback);
    }
}
