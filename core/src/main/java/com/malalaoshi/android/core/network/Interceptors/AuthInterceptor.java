package com.malalaoshi.android.core.network.Interceptors;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by kang on 16/12/19.
 */

public class AuthInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request originalRequest = chain.request();
       /* if (Your.sToken == null || alreadyHasAuthorizationHeader(originalRequest)) {
           return chain.proceed(originalRequest);
        }*/
        Request authorised = originalRequest.newBuilder()
                //.header("Authorization", Your.sToken)
                .build();
        Response response = chain.proceed(authorised);
        //判断token是否失效  401

        return response;
    }
}
