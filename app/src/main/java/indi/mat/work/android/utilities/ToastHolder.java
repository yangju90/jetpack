package indi.mat.work.android.utilities;

import android.content.Context;

public class ToastHolder {


    public static void showToast(Context context, String msg) {

    }

    public static void showToast(String msg) {
        showToast(MainApplication.getAppContext(), msg);
    }

    public static void showErrorToast(String msg) {

    }
}
