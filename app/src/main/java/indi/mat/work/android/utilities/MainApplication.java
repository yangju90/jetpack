package indi.mat.work.android.utilities;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import indi.mat.work.android.util.CrashHandler;

public class MainApplication extends Application {

    private static MainApplication instance;
    private static Context appContext;

    public static MainApplication getInstance() {
        return instance;
    }

    public static Context getAppContext() {
        return appContext;
    }

    public void setAppContext(Context mAppContext) {
        this.appContext = mAppContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        this.setAppContext(getApplicationContext());

        AppVersion.init(appContext);
        UserInfoStore.init();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);

        //开启崩溃日志收集
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(getAppContext());
    }
}
