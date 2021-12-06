package indi.mat.work.android.net.interceptor;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class UriInterceptor implements Interceptor {
    @NonNull
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        List<String> pathSegments = request.url().encodedPathSegments();
        HttpUrl.Builder builder = request.url().newBuilder();
        for(int i = 0; i < pathSegments.size(); i++) builder.setEncodedPathSegment(i, pathSegments.get(i));

        return chain.proceed(request.newBuilder()
                .url(builder.build()).build());
    }
}
