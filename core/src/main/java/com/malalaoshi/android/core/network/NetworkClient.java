package com.malalaoshi.android.core.network;

import com.malalaoshi.android.core.AppContext;
import com.malalaoshi.android.core.network.Interceptors.AuthInterceptor;
import com.malalaoshi.android.core.network.api.BaseApiCallback;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by kang on 16/12/9.
 */

public class NetworkClient {
    private static Retrofit mRetrofit;

    public static Retrofit retrofit() {
        if (mRetrofit == null) {
            synchronized (NetworkClient.class){
               if (mRetrofit ==null){
                   AuthInterceptor authInterceptor = new AuthInterceptor();
                   OkHttpClient.Builder builder = new OkHttpClient.Builder();
                   builder.connectTimeout(30, TimeUnit.SECONDS);
                   if (AppContext.isDebug()){
                       //设置拦截器，以用于自定义Cookies的设置
                       builder.addNetworkInterceptor(new HttpLoggingInterceptor());
                   }
                   //增加认证拦截器
                   builder.addNetworkInterceptor(authInterceptor);
                   //设置缓存目录
                  /* File cacheDirectory = new File(context.getCacheDir()
                           .getAbsolutePath(), "HttpCache");
                   Cache cache = new Cache(cacheDirectory, 20 * 1024 * 1024);
                   builder.cache(cache);*/
                   OkHttpClient okHttpClient = builder.build();
                   //构建Retrofit
                   mRetrofit = new Retrofit.Builder()
                           //配置服务器路径
                           .baseUrl(BaseApiCallback.getHost())
                           //配置转化库，默认是Gson
                           .addConverterFactory(GsonConverterFactory.create())
                           //配置回调库，采用RxJava
                           .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                           //设置OKHttpClient为网络客户端
                           .client(okHttpClient)
                           .build();
               }
            }
        }
        return mRetrofit;
    }
}
