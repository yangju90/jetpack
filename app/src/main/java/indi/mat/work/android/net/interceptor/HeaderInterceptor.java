package com.newegg.logistics.net.interceptor;

import androidx.annotation.NonNull;

import com.newegg.logistics.BuildConfig;
import com.newegg.logistics.model.base.TokenRefresh;
import com.newegg.logistics.model.reponse.login.TokenRefreshResponseInfo;
import com.newegg.logistics.net.base.RetrofitManagement;
import com.newegg.logistics.net.config.ApiCode;
import com.newegg.logistics.net.service.LoginService;
import com.newegg.logistics.utilities.MainApplication;
import com.newegg.logistics.utilities.UserInfoStore;

import java.io.IOException;

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
            TokenRefreshResponseInfo responseInfo = getNewToken(UserInfoStore.getUser().getToken(), UserInfoStore.getUser().getRefreshToken());
            if(!responseInfo.getStatus()) {
                return response;
            }

            UserInfoStore.getUser().setToken(responseInfo.getData().getToken());
            UserInfoStore.getUser().setRefreshToken(responseInfo.getData().getRefreshToken());

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
            builder.addHeader("x-neplatform-jwt", UserInfoStore.getUser().getToken());
            builder.addHeader("X-WarehouseNumber", UserInfoStore.getUser().getCurrWh());
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
            }
        } catch (IOException e) {
        }
        if(ans == null) {
            ans = new TokenRefreshResponseInfo();
            ans.setResultCode(ApiCode.REFRESH_TOKEN_ERROR.getCode());
        }

        return ans;
    }
}
