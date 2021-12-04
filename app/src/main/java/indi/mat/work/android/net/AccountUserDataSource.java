package com.newegg.logistics.net;


import com.newegg.logistics.model.reponse.login.AccountUserResponseInfo;
import com.newegg.logistics.model.request.login.LoginRequestInfo;
import com.newegg.logistics.net.base.BaseRemoteDataSource;
import com.newegg.logistics.net.service.LoginService;


import java.io.IOException;

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
