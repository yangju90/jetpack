package com.newegg.logistics.net.service;

import com.newegg.logistics.behavior_collect.entity.EventPoint;
import com.newegg.logistics.model.base.BaseResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UploadLogService {
    @POST("api/behavior/save")
    Call<BaseResponse> upLoad(@Body List<EventPoint> eventPointList);
}
