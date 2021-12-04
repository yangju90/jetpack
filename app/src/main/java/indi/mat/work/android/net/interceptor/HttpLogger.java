package com.newegg.logistics.net.interceptor;

import androidx.annotation.NonNull;

import com.newegg.logistics.utilities.LogUtil;

import okhttp3.logging.HttpLoggingInterceptor;

public class HttpLogger implements HttpLoggingInterceptor.Logger {
    private final String TAG = "HttpLogger";
    @Override
    public void log(@NonNull String s) {
        LogUtil.d(TAG, s);
    }
}
