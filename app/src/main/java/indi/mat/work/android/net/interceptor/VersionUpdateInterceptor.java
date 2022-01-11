package indi.mat.work.android.net.interceptor;

import androidx.annotation.NonNull;


import java.io.IOException;

import indi.mat.work.android.utilities.AppVersion;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class VersionUpdateInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder builder = request.newBuilder()
                .addHeader("x-version-code", AppVersion.getVersionName());
        Response response = chain.proceed(builder.build());
        if(Boolean.parseBoolean(response.header("x-app-update"))) AppVersion.setAppUpdate(true);
        return response;
    }
}
