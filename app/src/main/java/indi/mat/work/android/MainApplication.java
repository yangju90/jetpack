package indi.mat.work.android;

import android.app.Application;
import android.content.Context;

import indi.mat.work.android.model.User;
import indi.mat.work.android.util.CrashHandler;

public class MainApplication extends Application {
    private static Context context;
    private static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        CrashHandler crashHandler = CrashHandler.getInstance();
        crashHandler.init(context);
    }

    public static Context getContext() {
        return context;
    }

    public static User getUser(){
        return user;
    }

    public static User getCloneUser(){
        if(user != null) return user.getUser();
        return null;
    }


    public static void setUser(User user) {
        MainApplication.user = user;
    }
}
