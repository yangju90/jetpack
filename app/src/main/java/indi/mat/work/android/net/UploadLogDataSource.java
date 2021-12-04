package com.newegg.logistics.net;

import com.newegg.logistics.behavior_collect.entity.EventPoint;
import com.newegg.logistics.model.base.BaseResponse;
import com.newegg.logistics.model.reponse.log.UploadLogResponseInfo;
import com.newegg.logistics.model.reponse.login.AccountUserResponseInfo;
import com.newegg.logistics.model.request.login.LoginRequestInfo;
import com.newegg.logistics.net.base.BaseRemoteDataSource;
import com.newegg.logistics.net.service.LoginService;
import com.newegg.logistics.net.service.UploadLogService;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class UploadLogDataSource extends BaseRemoteDataSource<UploadLogService> {

    @Override
    protected UploadLogService getService() {
        return getService(UploadLogService.class);
    }

    public void upload(List<EventPoint> eventPointList, Callback<BaseResponse> callback){
        getService().upLoad(eventPointList).enqueue(callback);
    }
}
