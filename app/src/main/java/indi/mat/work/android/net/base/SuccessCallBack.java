package indi.mat.work.android.net.base;

import indi.mat.work.android.model.base.BaseResponse;
import retrofit2.Call;
import retrofit2.Response;

public interface SuccessCallBack<T extends BaseResponse> {

    void onResponse(Call<T> call, Response<T> response);
}
