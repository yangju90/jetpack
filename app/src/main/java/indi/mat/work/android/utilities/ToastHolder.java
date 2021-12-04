package com.newegg.logistics.utilities;

import android.content.Context;
import android.widget.Toast;

import com.newegg.logistics.R;
import com.newegg.logistics.widget.IconToast;

public class ToastHolder {

    private static IconToast toast;

    public static void showToast(Context context, String msg) {
        try {

            if(toast==null){
                toast=new IconToast(context);
            }
            String toastStr = msg + "";
            toast.setIconId(R.drawable.ic_toast_smlie_tip);
            toast.show(toastStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showToast(String msg) {
        showToast(MainApplication.getAppContext(), msg);
    }

    public static void showErrorToast(String msg) {
        try {
            String toastStr = msg + "";
            if (toast == null) {
                toast = new IconToast(MainApplication.getAppContext());
            }else{
                toast.cancel();
                toast = new IconToast(MainApplication.getAppContext());
            }
            toast.setIconId(R.drawable.ic_toast_sad_95_95);
            toast.show(toastStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
