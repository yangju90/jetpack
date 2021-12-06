package indi.mat.work.android.net.service;

import indi.mat.work.android.model.base.TokenRefresh;
import indi.mat.work.android.model.reponse.login.AccountUserResponseInfo;
import indi.mat.work.android.model.reponse.login.TokenRefreshResponseInfo;
import indi.mat.work.android.model.request.login.LoginRequestInfo;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface LoginService {
    @POST("api/login")
    Call<AccountUserResponseInfo> accountUserLogin(@Body LoginRequestInfo loginRequestInfo);

    @POST("api/login")
    Call<TokenRefreshResponseInfo> refreshToken(@Body TokenRefresh tokenRefresh);

}
