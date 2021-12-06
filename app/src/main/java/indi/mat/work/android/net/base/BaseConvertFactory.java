package indi.mat.work.android.net.base;

import androidx.annotation.Nullable;

import com.google.gson.Gson;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class BaseConvertFactory extends Converter.Factory {

    private static final MediaType MEDIA_TYPE = MediaType.get("text/plain; charset=UTF-8");

    public static BaseConvertFactory create() {
        return create(new Gson());
    }


    @SuppressWarnings("ConstantConditions")
    public static BaseConvertFactory create(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        return new BaseConvertFactory(gson);
    }

    private final Gson gson;

    private BaseConvertFactory(Gson gson) {
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(
            Type type, Annotation[] annotations, Retrofit retrofit) {
        if(type != String.class) return null;
        return new BaseResponseBodyConverter();
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(
            Type type,
            Annotation[] parameterAnnotations,
            Annotation[] methodAnnotations,
            Retrofit retrofit) {
        if(type != String.class) return null;
        return new BaseRequestBodyConverter();
    }

    class BaseResponseBodyConverter implements Converter<ResponseBody, String>{

        @Nullable
        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }

    class BaseRequestBodyConverter implements Converter<String, RequestBody> {

        @Nullable
        @Override
        public RequestBody convert(String value) throws IOException {
            return RequestBody.create(MEDIA_TYPE, String.valueOf(value));
        }
    }
}
