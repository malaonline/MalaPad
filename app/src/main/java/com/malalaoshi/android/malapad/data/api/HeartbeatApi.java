package com.malalaoshi.android.malapad.data.api;

import com.malalaoshi.android.core.network.NetworkClient;
import com.malalaoshi.android.malapad.data.api.param.HeartbeatParam;
import com.malalaoshi.android.malapad.data.api.response.HeartbeatResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by kang on 16/12/29.
 */

public class HeartbeatApi extends BaseApi {

    private interface HeartbeatService {
        @POST("/api/v1/pad/status")
        Call<HeartbeatResponse> heartbeat(@Header("token") String token, @Body HeartbeatParam param);
    }
    protected static final HeartbeatApi.HeartbeatService service  = NetworkClient.retrofit().create(HeartbeatApi.HeartbeatService.class);

    public static Call<HeartbeatResponse> heartbeat(HeartbeatParam param){
        return service.heartbeat(getToken(),param);
    }
}
