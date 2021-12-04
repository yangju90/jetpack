package com.newegg.logistics.net.base;

import com.newegg.logistics.model.base.BaseResponse;

import retrofit2.Call;
import retrofit2.Response;

public interface SuccessCallBack<T extends BaseResponse> {

    void onResponse(Call<T> call, Response<T> response);
}
