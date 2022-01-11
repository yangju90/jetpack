package indi.mat.work.android.utilities;


import android.content.Context;
import android.widget.Toast;


public class ToastHolder {

    private static Toast toast;

    public static void showToast(Context context, String msg) {
        if(toast == null){
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
//            toast.setGravity(Gravity.CENTER, 0, 0);
        }else{
            toast.cancel();
            toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            toast.setDuration(Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    public static void showToast(String msg) {
        showToast(MainApplication.getAppContext(), msg);
    }
}
