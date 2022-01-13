package indi.mat.work.android.utilities;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.text.InputType;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import io.reactivex.internal.operators.parallel.ParallelDoOnNextTry;

public class KeyWordsUtil {

    // 弹出虚拟键盘
    public static void showSoftInputFromWindow(EditText editText){
        editText.postDelayed(new Runnable() {
            @Override
            public void run() {
                editText.setFocusable(true);
                editText.setFocusableInTouchMode(true);
                editText.requestFocus();
                InputMethodManager inputMethodManager = (InputMethodManager) editText.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.showSoftInput(editText, 0);
            }
        }, 1);
    }


    public static void closeKeyBoard(EditText editText, Activity activity){
        int SDK_INT = Build.VERSION.SDK_INT;
        if(SDK_INT <= 10){
            // 点击EditText， 屏蔽默认输入法
            editText.setInputType(InputType.TYPE_NULL);
        }else{
            // 点击EditText，隐藏系统输入法
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

            try {
                Class<EditText> clazz = EditText.class;
                // 4.0 是 setShowSoftInputOnFocus 4.2 是 setSoftInputShownOnFocus
                Method method = clazz.getMethod("setShowSoftInputOnFocus", boolean.class);
                method.setAccessible(true);
                method.invoke(editText, false);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }
    }

}
