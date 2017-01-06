package com.malalaoshi.android.malapad.data.heartbeat;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.malalaoshi.android.core.event.BusEvent;
import com.malalaoshi.android.core.event.BusEventDef;
import com.malalaoshi.android.malapad.data.api.HeartbeatApi;
import com.malalaoshi.android.malapad.data.api.param.HeartbeatParam;
import com.malalaoshi.android.malapad.data.api.response.HeartbeatResponse;
import com.malalaoshi.android.malapad.data.entity.Heartbeat;
import com.malalaoshi.android.malapad.usercenter.UserManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by kang on 17/1/5.
 */

public class HeartbeatThread extends Thread {

    private String questionsId;
    private String classRoomId;
    private boolean flag;

    public HeartbeatThread(String classRoomId) {
        this.classRoomId = classRoomId;
        this.flag = true;
    }

    @Override
    public void run() {
        super.run();
        UserManager userManager = UserManager.getInstance();
        while (flag){
            Call<HeartbeatResponse> call = HeartbeatApi.heartbeat(new HeartbeatParam(classRoomId));
            try {
                Response<HeartbeatResponse> response = call.execute();
                if (response.isSuccessful()) {
                    HeartbeatResponse heartbeat = response.body();
                    if (heartbeat.getCode()==0){

                    }else if (heartbeat.getCode()==-1){
                        userManager.userLogout();
                        break;
                    }else if (heartbeat.getCode()==-2){
                        userManager.tokenInvalid();
                        break;
                    }
                } else {
                    int statusCode = response.code();
                    // handle request errors yourself
                    ResponseBody errorBody = response.errorBody();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void stopThread(){
        flag= false;
        interrupt();
    }


}
