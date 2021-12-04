package com.newegg.logistics.utilities;

import android.app.Application;
import android.content.Context;

import androidx.appcompat.app.AppCompatDelegate;

import com.newegg.logistics.behavior_collect.BehaviorSDK;
import com.newegg.logistics.common.storage.sp.SPUtils;
import com.newegg.logistics.data.UploadLogRepository;

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

        UserInfoStore.init();

        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        //初始化全局的SPUtils
        SPUtils.init(getAppContext());
        //初始化用户收集并开启收集设置.fileName为assets文件夹下Json文件.
        new BehaviorSDK.Initer(MainApplication.getAppContext(),getPackageName(),"trackSettings.json")
                .isCollectClickEvent(true)
                .isCollectLifeEvent(false)
                .init()
                .setUploadLogService(UploadLogRepository.getInstance());

        //开启崩溃日志收集
        CrashHandler crashHandler = CrashHandler.getCrashHander();
        crashHandler.init(this);
    }
}
