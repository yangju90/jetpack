package indi.mat.work.android.network;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import indi.mat.work.android.network.interceptor.HeaderInterceptor;
import indi.mat.work.android.network.interceptor.LoggingInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitManagement {
    private static final long READ_TIMEOUT = 6000;

    private static final long WRITE_TIMEOUT = 6000;

    private static final long CONNECT_TIMEOUT = 6000;

    private INetworkRequiredInfo iNetworkRequiredInfo = null;

    private final Map<String, Retrofit> serviceMap = new ConcurrentHashMap<>();

    private RetrofitManagement() {
    }

    public static RetrofitManagement getInstance() {
        return RetrofitHolder.retrofitManagement;
    }

    private static class RetrofitHolder {
        private static final RetrofitManagement retrofitManagement = new RetrofitManagement();
    }

    private Retrofit createRetrofit(String url) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
//
        if (iNetworkRequiredInfo != null) {
//            builder.addInterceptor(new HttpInterceptor());
            builder.addInterceptor(new HeaderInterceptor(iNetworkRequiredInfo));
//            builder.addInterceptor(new FilterInterceptor())
        }
//
        builder.retryOnConnectionFailure(true);
        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new LoggingInterceptor());
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(httpLoggingInterceptor);
//            builder.addInterceptor(new MonitorInterceptor(ContextHolder.getContext()));
        }
        OkHttpClient client = builder.build();
        return new Retrofit.Builder()
                .client(client)
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

//    <T> ObservableTransformer<BaseResponseBody<T>, T> applySchedulers() {
//        return observable -> observable.subscribeOn(Schedulers.io())
//                .unsubscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .flatMap(result -> {
//                    switch (result.getCode()) {
//                        case HttpCode.CODE_SUCCESS: {
//                            return createData(result.getData());
//                        }
//                        case HttpCode.CODE_TOKEN_INVALID: {
//                            throw new TokenInvalidException();
//                        }
//                        case HttpCode.CODE_ACCOUNT_INVALID: {
//                            throw new AccountInvalidException();
//                        }
//                        default: {
//                            throw new ServerResultException(result.getCode(), result.getMsg());
//                        }
//                    }
//                });
//    }


//    private <T> Observable<T> createData(T t) {
//        return Observable.create(new ObservableOnSubscribe<T>() {
//            @Override
//            public void subscribe(ObservableEmitter<T> emitter) {
//                try {
//                    emitter.onNext(t);
//                    emitter.onComplete();
//                } catch (Exception e) {
//                    emitter.onError(e);
//                }
//            }
//        });
//    }


    public void setINetworkRequiredInfo(INetworkRequiredInfo iNetworkRequiredInfo) {
        this.iNetworkRequiredInfo = iNetworkRequiredInfo;
    }

    <T> T getService(Class<T> clz, String host) {
        Retrofit retrofit;
        if (serviceMap.containsKey(host)) {
            retrofit = serviceMap.get(host);
        } else {
            retrofit = createRetrofit(host);
            serviceMap.put(host, retrofit);
        }
        return retrofit.create(clz);
    }
}
