package com.malalaoshi.android.malapad.data.api;

import android.util.Log;

import com.malalaoshi.android.core.AppContext;
import com.malalaoshi.android.core.entity.AuthUser;
import com.malalaoshi.android.core.network.NetworkClient;
import com.malalaoshi.android.malapad.data.api.param.LoginParam;
import com.malalaoshi.android.malapad.data.api.response.LoginResponse;
import com.malalaoshi.android.malapad.data.entity.User;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kang on 16/12/27.
 */

public class LoginApi {
    public static class ErrorCode{
        public static int ERROR_CODE_ILLEGAL_USER = -1;
        public static int ERROR_CODE_NO_LESSON = -2;
        public static int ERROR_CODE_ILLEGAL_PHONE = -3;
        public static int ERROR_CODE_BAD_NET = -4;
    }

    private interface LoginService {
        @POST("/api/v1/pad/login")
        public Observable<LoginResponse> login(@Body LoginParam param);
    }

    protected static final LoginService service  = NetworkClient.retrofit().create(LoginService.class);

    public static Observable<LoginResponse> login(LoginParam param){
        Log.e("api","param:"+param.getPhone());
        return service.login(param);
    }
}
