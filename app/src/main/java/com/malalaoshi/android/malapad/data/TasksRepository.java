package com.malalaoshi.android.malapad.data;

import android.util.Log;

import com.malalaoshi.android.core.network.api.BaseApiCallback;
import com.malalaoshi.android.core.network.response.BaseResponse;
import com.malalaoshi.android.malapad.usercenter.UserManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kang on 16/12/26.
 */

public class TasksRepository <T extends BaseResponse>{
    private static String TAG = "TasksRepository";
    private BaseApiCallback<T> callback = null;
    private boolean hasAuthor = false;
    public TasksRepository(BaseApiCallback<T>  callback, boolean hasAuthor){
        this.callback = callback;
        this.hasAuthor = hasAuthor;
    }

    public  Subscription addTask(Observable<T> observable){
     return observable.subscribeOn(Schedulers.io())
            .doOnSubscribe(
            ()->{
                callback.onApiStarted();
            })
            .subscribeOn(AndroidSchedulers.mainThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
            response -> {
                Log.e("api",response.getCode()+"  "+response.getMsg());
                if (response.getCode()==0){
                    callback.onSuccess(response);
                }else{
                    if (hasAuthor){
                        checkAuthError(response);
                    }
                    callback.onFailure(response.getCode(),response.getMsg());
                }
            },
             throwable -> {
                     callback.onFailure(BaseApiCallback.ERROR_CODE_BAD_NET, throwable.getMessage());
             },
            () -> {
                callback.onFinish();
            }
            );
    }

    public void addTask(Call<T> call){
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                T body = response.body();
                if (body==null){
                    Log.e(TAG,"repose is null");
                    callback.onFailure(BaseApiCallback.ERROR_CODE_NULL_EXECPTION,"response is null");
                    return;
                }
                if (body.getCode()==0){
                    callback.onSuccess(body);
                }else{
                    if (hasAuthor){
                        checkAuthError(body);
                    }
                    callback.onFailure(body.getCode(),body.getMsg());
                }
            } else {
                int statusCode = response.code();
                // handle request errors yourself
                ResponseBody errorBody = response.errorBody();
                callback.onFailure(statusCode,errorBody.string());
                Log.e(TAG,"response failed, status Code:"+statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
            callback.onFailure(BaseApiCallback.ERROR_CODE_IO_EXECPTION,e.getMessage());
            Log.e(TAG,"response IOException:"+e.getMessage());
        }
    }


    private void checkAuthError(T response) {
        switch (response.getCode()){
            case BaseApiCallback.ERROR_CODE_TOKEN_INVALID:
                UserManager.getInstance().tokenInvalid();
                break;
            case BaseApiCallback.ERROR_CODE_TOKEN_LOGOUT:
                UserManager.getInstance().userLogout();
                break;
            default:
                break;
        }
    }
}
