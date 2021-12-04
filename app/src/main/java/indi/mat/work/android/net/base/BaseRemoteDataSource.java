package com.newegg.logistics.net.base;


import com.newegg.logistics.net.config.ApiCode;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public abstract class BaseRemoteDataSource<T> {

    protected abstract T getService();

    protected T getService(Class<T> cls){
        return RetrofitManagement.getInstance().getService(cls);
    }


    protected Map<String, String> objectToMap(Object object){
        Map<String, String> map = new HashMap<>();
        Class cls = object.getClass();
        for(Field field :cls.getDeclaredFields()){
            field.setAccessible(true);
            String fieldName = field.getName();
            Object val = null;
            try {
                val = field.get(object);
            } catch (IllegalAccessException e) {
                throw new BaseException(ApiCode.DATA_CONVERT_ERROR);
            }
            if("null".equals(String.valueOf(val))) continue;
            map.put(fieldName, String.valueOf(val));
        }

        return map;
    }
}
