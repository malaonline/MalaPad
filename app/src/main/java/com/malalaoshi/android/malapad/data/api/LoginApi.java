package com.malalaoshi.android.malapad.data.api;

import com.malalaoshi.android.core.entity.AuthUser;
import com.malalaoshi.android.core.network.NetworkClient;
import com.malalaoshi.android.malapad.data.api.param.LoginParam;
import com.malalaoshi.android.malapad.data.entity.User;

import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by kang on 16/12/27.
 */

public class LoginApi {
    public static class ErrorCode{
        public static int ERROR_CODE_ILLEGAL_PHONE = -1;
        public static int ERROR_CODE_ILLEGAL_USER = -2;
        public static int ERROR_CODE_NO_COURSE = -3;
        public static int ERROR_CODE_BAD_NET = -4;
    }

    private interface LoginService {
        @POST("/api/v1/sms")
        public Observable<User> login(@Body LoginParam param);
    }

    protected static final LoginService service  = NetworkClient.retrofit().create(LoginService.class);

    public static Observable<User> login(LoginParam param){
        return service.login(param);
    }
}
