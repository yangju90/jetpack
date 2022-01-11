package indi.mat.work.behavior_collect;

import android.content.Context;

import java.lang.reflect.Method;

public class DbUtil {
    private BehaviorDatabase database;
    private static DbUtil instance;
    private Method[] methods;

    private DbUtil(Context context){
        database = BehaviorDatabase.getInstance(context);
    }
}
