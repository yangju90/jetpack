package indi.mat.work.android.net.base;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import indi.mat.work.android.BuildConfig;
import indi.mat.work.android.net.interceptor.HeaderInterceptor;
import indi.mat.work.android.net.interceptor.HttpLogger;
import indi.mat.work.android.net.interceptor.UriInterceptor;
import indi.mat.work.android.net.interceptor.VersionUpdateInterceptor;
import indi.mat.work.android.utilities.UserInfoStore;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitManagement {

    private static final long READ_TIMEOUT = 60000;
    private static final long WRITE_TIMEOUT = 60000;
    private static final long CONNECT_TIMEOUT = 60000;

    private final Map<String, Object> serviceMap = new ConcurrentHashMap<>();

    public RetrofitManagement() {
    }

    public static RetrofitManagement getInstance(){
        return RetrofitHolder.retrofitManagement;
    }

    private static class RetrofitHolder{
        private static final RetrofitManagement retrofitManagement = new RetrofitManagement();
    }

    private Retrofit createRetrofit(String baseUrl){
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(new UriInterceptor())
                .addInterceptor(new HeaderInterceptor())
                .addInterceptor(new VersionUpdateInterceptor())
                .retryOnConnectionFailure(true);

        if(BuildConfig.DEBUG){
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLogger());
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
        }

        OkHttpClient okHttpClient = builder.build();
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(baseUrl)
                .addConverterFactory(BaseConvertFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    public <T> T getService(Class<T> cls){
        return getService(cls, UserInfoStore.getUser().getUrl());
    }

    private <T> T getService(Class<T> cls, String baseUrl){
        String key = baseUrl  + cls.toString();
        T value;
        if (serviceMap.containsKey(key)) {
            Object obj = serviceMap.get(key);
            if (obj == null) {
                value = createRetrofit(baseUrl).create(cls);
                serviceMap.put(key, value);
            } else {
                value = (T) obj;
            }
        } else {
            value = createRetrofit(baseUrl).create(cls);
            serviceMap.put(key, value);
        }
        return value;
    }

}
