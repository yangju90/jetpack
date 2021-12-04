package com.newegg.logistics.net.service;

import com.newegg.logistics.model.base.TokenRefresh;
import com.newegg.logistics.model.reponse.login.AccountUserResponseInfo;
import com.newegg.logistics.model.reponse.login.TokenRefreshResponseInfo;
import com.newegg.logistics.model.request.login.LoginRequestInfo;
import com.newegg.logistics.utilities.UserInfoStore;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface LoginService {
    @POST("api/login")
    Call<AccountUserResponseInfo> accountUserLogin(@Body LoginRequestInfo loginRequestInfo);

    @POST("api/login")
    Call<TokenRefreshResponseInfo> refreshToken(@Body TokenRefresh tokenRefresh);

}
