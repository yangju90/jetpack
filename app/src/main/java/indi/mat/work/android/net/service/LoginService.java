package indi.mat.work.android.net.service;


import indi.mat.work.android.model.base.TokenRefresh;
import indi.mat.work.android.model.reponse.login.AccountUserResponseInfo;
import indi.mat.work.android.model.reponse.login.TokenRefreshResponseInfo;
import indi.mat.work.android.model.request.login.LoginRequestInfo;
import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LoginService {
    @POST("api/login")
    Observable<Response<AccountUserResponseInfo>> accountUserLogin(@Body LoginRequestInfo loginRequestInfo);

    @POST("/api/jwt/refresh")
    Call<TokenRefreshResponseInfo> refreshToken(@Body TokenRefresh tokenRefresh);
}
