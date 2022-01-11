package indi.mat.work.android.utilities;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

public class AppVersion {
    private static String versionName;
    private static Boolean appUpdate;

    public static String getVersionName() {
        return versionName;
    }

    public static void setVersionName(String version) {
        versionName = version;
    }

    public static Boolean getAppUpdate() {
        return appUpdate;
    }

    public static void setAppUpdate(Boolean isUpdate) {
        appUpdate = isUpdate;
    }

    public static void init(Context context){
        appUpdate = false;
        versionName = getVersionName(context);
    }


    /**
     * get App versionName
     * @param context
     * @return
     */
    private static String getVersionName(Context context){
        PackageManager packageManager=context.getPackageManager();
        PackageInfo packageInfo;
        String versionName="";
        try {
            packageInfo=packageManager.getPackageInfo(context.getPackageName(),0);
            versionName=packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return versionName;
    }
}
