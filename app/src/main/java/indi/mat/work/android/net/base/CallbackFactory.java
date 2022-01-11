package indi.mat.work.android.net.base;


import com.google.gson.Gson;

import indi.mat.work.android.model.base.BaseResponse;
import indi.mat.work.android.net.config.ApiCode;
import indi.mat.work.android.net.config.ApiCodeUtil;
import indi.mat.work.android.utilities.ToastHolder;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackFactory {

    public CallbackFactory() {
    }

    public <R extends BaseResponse> InnerCallBack<R> create(SuccessCallBack<R> successCallBack) {
        return new InnerCallBack<R>() {
            @Override
            public void onSuccess(Call<R> call, Response<R> response) {
                if (!response.body().getStatus()) {
                    if (ApiCodeUtil.getApiCode(response.body().getStatusCode()) != null) {
                        ToastHolder.showToast(ApiCodeUtil.getApiCode(response.body().getStatusCode()).getReasonPhrase());
                    } else {
                        ToastHolder.showToast(response.body().getMessage());
                    }
                }

                successCallBack.onResponse(call, response);
            }
        };
    }


    public <R extends BaseResponse> InnerCallBack<R> createWithoutToast(SuccessCallBack<R> successCallBack) {
        return new InnerCallBack<R>() {
            @Override
            public void onSuccess(Call<R> call, Response<R> response) {
                if (!response.body().getStatus()) {
                    if (ApiCodeUtil.getApiCode(response.body().getStatusCode()) != null) {
                        response.body().setMessage(ApiCodeUtil.getApiCode(response.body().getStatusCode()).getReasonPhrase());
                    }
                }
                successCallBack.onResponse(call, response);
            }
        };
    }


    private void loginOut() {
        NetStatusModel.getInstance().loginOut();
    }


    private abstract class InnerCallBack<T> implements Callback<T> {
        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if (isValidCode(response.code())) {
                onSuccess(call, response);
            } else if (response.code() == 401) {
                ToastHolder.showToast(ApiCode.CODE_DEMO_12003.getReasonPhrase());
                loginOut();
            } else {
                BaseResponse t = null;
                if ((t = errorBodyConvert2Json(response)) != null) {
                    if (ApiCodeUtil.getApiCode(t.getStatusCode()) != null) {
                        ToastHolder.showToast(ApiCodeUtil.getApiCode(t.getStatusCode()).getReasonPhrase());
                    } else {
                        ToastHolder.showToast(t.getMessage());
                    }
                } else {
                    ToastHolder.showToast(ApiCode.CODE_DEMO_50001.getReasonPhrase());
                }
            }
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            ToastHolder.showToast(t.getMessage());
        }

        private BaseResponse errorBodyConvert2Json(Response<T> response) {
            BaseResponse t = null;
            Gson gson = new Gson();
            try {
              t = gson.fromJson(response.errorBody().string(), BaseResponse.class);
            }catch (Exception e){

            }
            return t;
        }

        private boolean isValidCode(int code) {
            int t = code / 100;
            return t == 2 || t == 1;
        }

        public abstract void onSuccess(Call<T> call, Response<T> response);

    }
}
