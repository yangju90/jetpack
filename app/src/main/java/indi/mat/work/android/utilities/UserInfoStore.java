package indi.mat.work.android.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import indi.mat.work.android.BuildConfig;
import indi.mat.work.android.model.bean.User;

public class UserInfoStore {
    private static User user;

    private static final String DEFAULT = "default";

    public static User getUser(){
        return user;
    }

    public static void setUser(User u){
        user = u;
    }

    public static boolean isCertified(){
        return user != null && !DEFAULT.equals(UserInfoStore.getUser().getUserID());
    }

    public static boolean isLoginCompleted(){
        return isCertified() && user.getCurrWh() != null && user.getCurrWh().length() != 0;
    }


    public static void init(){
        user = readAccount();

        if(user == null) {
            user = new User();
            user.setUserID(DEFAULT);
            user.setUrl(BuildConfig.SERVICE_URL);
        }
    }

    private static Gson gson = new Gson();

    public static void saveAccount(){
        SharedPreferences sharedPreferences = MainApplication.getAppContext().getSharedPreferences(Constants.sharedPreferences_userInfo, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorMain = sharedPreferences.edit();
        editorMain.putString(Constants.sharedPreferences_userInfo, gson.toJson(user));
        editorMain.commit();
    }


    public static User readAccount(){
        SharedPreferences sharedPreferences = MainApplication.getAppContext().getSharedPreferences(Constants.sharedPreferences_userInfo, Context.MODE_PRIVATE);
        String userInfo = sharedPreferences.getString(Constants.sharedPreferences_userInfo, null);
        return gson.fromJson(userInfo, User.class);
    }


    public static void clear(){
        SharedPreferences sharedPreferences = MainApplication.getAppContext().getSharedPreferences(Constants.sharedPreferences_userInfo, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorMain = sharedPreferences.edit();
        editorMain.clear();
        editorMain.commit();
    }
}
