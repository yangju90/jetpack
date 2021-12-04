package com.newegg.logistics.net.base;


import com.newegg.logistics.base.BaseViewModel;
import com.newegg.logistics.model.base.BaseResponse;
import com.newegg.logistics.net.config.ApiCode;
import com.newegg.logistics.net.config.ApiCodeUtil;
import com.newegg.logistics.utilities.ToastHolder;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CallbackFactory {

    private BaseViewModel  baseViewModel;
    public CallbackFactory(BaseViewModel baseViewModel) {
        this.baseViewModel = baseViewModel;
    }

    public <R extends BaseResponse> InnerCallBack<R> create(SuccessCallBack<R> successCallBack){

        showLoading();

        return new InnerCallBack<R>(){
            @Override
            public void onSuccess(Call<R> call, Response<R> response) {
                if(!response.body().getStatus()){
//                    ToastHolder.showToast(ApiCodeUtil.getApiCode(response.body().getResultCode()).getReasonPhrase());
                    ToastHolder.showToast(response.body().getMessage());
                    response.body().setMessage(response.body().getMessage());
                }
                successCallBack.onResponse(call, response);
            }
        };
    }


    public void showLoading() {
        NetStatusModel.getInstance().startLoading();
    }

    public void dismissLoading() {
        NetStatusModel.getInstance().dismissLoading();
    }

    private void loginOut(){
        NetStatusModel.getInstance().loginOut();
    }


    private abstract class InnerCallBack<T> implements Callback<T> {
        @Override
        public void onResponse(Call<T> call, Response<T> response) {
            if(isValidCode(response.code())){
                onSuccess(call, response);
            } else if(response.code() == 401){
                ToastHolder.showToast(ApiCode.CODE_DEMO_12003.getReasonPhrase());
                loginOut();
            } else {
                ToastHolder.showToast(ApiCode.CODE_DEMO_50001.getReasonPhrase());
            }
            dismissLoading();
        }

        @Override
        public void onFailure(Call<T> call, Throwable t) {
            ToastHolder.showToast(t.getMessage());
            dismissLoading();
        }

        private boolean isValidCode(int code){
            int t = code / 100;
            return t == 2 || t ==1;
        }

        public abstract void onSuccess(Call<T> call, Response<T> response);

    }
}
