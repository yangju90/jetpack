package indi.mat.work.android.net.config;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class ApiCodeUtil {
    public static HashMap<Integer, ApiCode> cache = null;

    static {
        cache = (HashMap<Integer, ApiCode>) Arrays.stream(ApiCode.values()).collect(Collectors.toMap(ApiCode::getCode, ApiCode -> ApiCode));
    }

    public static ApiCode getApiCode(int code){
        if(cache.containsKey(code)){
            return cache.get(code);
        }
        return cache.get(99999);
    }
}
