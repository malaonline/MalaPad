package com.malalaoshi.android.malapad.data.api;

import com.malalaoshi.android.core.network.NetworkClient;
import com.malalaoshi.android.malapad.data.api.response.HeartbeatResponse;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kang on 16/12/29.
 */

public class HeartbeatApi {

    private interface HeartbeatService {
        @POST("/api/v1/pad/heartbeat")
        public HeartbeatResponse heartbeat();
    }
    protected static final HeartbeatApi.HeartbeatService service  = NetworkClient.retrofit().create(HeartbeatApi.HeartbeatService.class);

    public static HeartbeatResponse heartbeat(){
        return service.heartbeat();
    }
}
