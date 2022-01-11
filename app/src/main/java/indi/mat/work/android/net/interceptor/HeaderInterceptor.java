package indi.mat.work.android.net.interceptor;

import androidx.annotation.NonNull;



import java.io.IOException;

import indi.mat.work.android.model.base.TokenRefresh;
import indi.mat.work.android.model.reponse.login.TokenRefreshResponseInfo;
import indi.mat.work.android.net.base.RetrofitManagement;
import indi.mat.work.android.net.config.ApiCode;
import indi.mat.work.android.net.service.LoginService;
import indi.mat.work.android.utilities.UserInfoStore;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = setUserHeader(request);
        Response response = chain.proceed(builder.build());

        if(isTokenExpired(response) && UserInfoStore.isLoginCompleted()){
            TokenRefreshResponseInfo responseInfo = getNewToken(UserInfoStore.getUser().getTokenRefresh().getToken(), UserInfoStore.getUser().getTokenRefresh().getRefreshToken());
            if(!responseInfo.getStatus()) {
                return response;
            }

            UserInfoStore.getUser().setTokenRefresh(responseInfo.getData());

            UserInfoStore.saveAccount();

            return chain.proceed(setUserHeader(request).build());
        }
        return response;
    }


    private boolean isTokenExpired(Response response) {
        if (response.code() == 401) {
            return true;
        }
        return false;
    }

    private Request.Builder setUserHeader(Request request){
        Request.Builder builder = request.newBuilder();
        builder.addHeader("Accept-Encoding", "gzip")
                .addHeader("Accept", "application/json")
                .addHeader("Content-Type", "application/json; charset=utf-8");

        if(UserInfoStore.isLoginCompleted()){
            builder.addHeader("X-UserID", UserInfoStore.getUser().getUserID());
            builder.addHeader("x-neplatform-jwt", UserInfoStore.getUser().getTokenRefresh().getToken());
            builder.addHeader("x-warehouse-no", UserInfoStore.getUser().getCurrWh());
        }

        return builder;
    }


    private TokenRefreshResponseInfo getNewToken(String token, String refreshToken){
        TokenRefresh tokenRefresh = new TokenRefresh();
        tokenRefresh.setToken(token);
        tokenRefresh.setRefreshToken(refreshToken);
        retrofit2.Response<TokenRefreshResponseInfo> rResponseInfo = null;
        TokenRefreshResponseInfo ans = null;
        try {
            rResponseInfo = RetrofitManagement.getInstance().getService(LoginService.class).refreshToken(tokenRefresh).execute();
            if(rResponseInfo.code() == 200){
                ans = rResponseInfo.body();
            }else if(rResponseInfo.code() == 402){
                ans = new TokenRefreshResponseInfo();
                ans.setStatus(false);
                ans.setStatusCode(ApiCode.CODE_DEMO_12003.getCode());
                ans.setMessage(ApiCode.CODE_DEMO_12003.getReasonPhrase());
            }
        } catch (IOException e) {
        }
        if(ans == null) {
            ans = new TokenRefreshResponseInfo();
            ans.setStatus(false);
            ans.setStatusCode(ApiCode.REFRESH_TOKEN_ERROR.getCode());
            ans.setMessage(ApiCode.REFRESH_TOKEN_ERROR.getReasonPhrase());
        }

        return ans;
    }
}
