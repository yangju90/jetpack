package com.newegg.logistics.data;

import com.google.gson.Gson;
import com.newegg.logistics.base.BaseViewModel;
import com.newegg.logistics.behavior_collect.BehaviorSDK;
import com.newegg.logistics.behavior_collect.entity.EventPoint;
import com.newegg.logistics.model.base.BaseResponse;
import com.newegg.logistics.model.reponse.login.AccountUserResponseInfo;
import com.newegg.logistics.net.AccountUserDataSource;
import com.newegg.logistics.net.UploadLogDataSource;
import com.newegg.logistics.net.base.BaseRepository;
import com.newegg.logistics.behavior_collect.net.UploadLogService;
import com.newegg.logistics.net.base.CallbackFactory;

import org.json.JSONObject;

import java.sql.SQLOutput;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadLogRepository implements UploadLogService {

    private UploadLogRepository instance;

    private UploadLogDataSource dataSource;

    private UploadLogRepository( ) {
        dataSource  = new UploadLogDataSource();
    }

    public static UploadLogRepository getInstance(){
        return Builder.instance;
    }

    @Override
    public void upLoad(List<EventPoint> eventPointList) {
        System.out.println("行为日志开始上传.......");

        dataSource.upload(eventPointList, new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                System.out.println("上传成功....." + response.body().getStatus());
                BehaviorSDK.getInstance().clearDB();
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {

            }
        });

        System.out.println("行为日志上传结束.......");
    }


    static class Builder{
        private static final UploadLogRepository instance = new UploadLogRepository();
    }
}
