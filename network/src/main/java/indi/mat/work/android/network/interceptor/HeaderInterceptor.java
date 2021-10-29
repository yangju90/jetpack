package indi.mat.work.android.network.interceptor;

import android.os.Build;

import androidx.annotation.RequiresApi;

import java.io.IOException;
import java.time.LocalDateTime;

import indi.mat.work.android.network.INetworkRequiredInfo;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    private INetworkRequiredInfo iNetworkRequiredInfo;

    public HeaderInterceptor(INetworkRequiredInfo networkRequiredInfo) {
        iNetworkRequiredInfo = networkRequiredInfo;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
        // Header拦截器
        builder.addHeader("Source", "source");
        builder.addHeader("appVersion", this.iNetworkRequiredInfo.getAppVersionName());
        builder.addHeader("Source", "source");
        builder.addHeader("Authorization", "Authorization");

        return chain.proceed(builder.build());
    }
}
