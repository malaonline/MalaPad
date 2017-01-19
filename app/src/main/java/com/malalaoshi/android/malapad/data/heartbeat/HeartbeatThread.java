package com.malalaoshi.android.malapad.data.heartbeat;

import android.util.Log;

import com.malalaoshi.android.core.event.EventDispatcher;
import com.malalaoshi.android.core.network.api.BaseApiCallback;
import com.malalaoshi.android.malapad.data.TasksRepository;
import com.malalaoshi.android.malapad.data.api.HeartbeatApi;
import com.malalaoshi.android.malapad.data.api.param.HeartbeatParam;
import com.malalaoshi.android.malapad.data.api.response.HeartbeatResponse;
import com.malalaoshi.android.malapad.data.entity.Heartbeat;
import com.malalaoshi.android.malapad.data.entity.QuestionGroup;
import com.malalaoshi.android.malapad.event.QuestionBusEvent;
import com.malalaoshi.android.malapad.usercenter.UserManager;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by kang on 17/1/5.
 */

public class HeartbeatThread extends Thread {
    private static String TAG="HeartbeatThread";
    private boolean flag;
    public HeartbeatThread() {
        this.flag = true;
    }

    @Override
    public void run() {
        super.run();
        UserManager userManager = UserManager.getInstance();
        while (flag){
            if (!userManager.isLogin()){
                try {
                    Thread.sleep(6000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                continue;
            }
            sendHeartbeatMessage(userManager.getClassRoom().getId()+"");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    private void sendHeartbeatMessage(String classRoomId) {
        Log.d(TAG,"send heartbeat message time:"+System.currentTimeMillis());
        Call<HeartbeatResponse> call = HeartbeatApi.heartbeat(new HeartbeatParam(classRoomId));
        new TasksRepository<HeartbeatResponse>(new BaseApiCallback<HeartbeatResponse>() {
            @Override
            public void onSuccess(HeartbeatResponse response) {
                sendQuestionMessage(response);
            }
        },true).addTask(call);
    }


    private void sendQuestionMessage(HeartbeatResponse response) {
        Heartbeat heartbeat = response.getData();
        if (heartbeat!=null&&heartbeat.getQuestionGroup()!=null&&heartbeat.getQuestionGroup().getId()!=null){
            QuestionGroup group = heartbeat.getQuestionGroup();
            EventDispatcher.getInstance().post(new QuestionBusEvent(response.getType(),group.getId()));
        }else {
            Log.e(TAG,"repose is null");
        }
    }

    public void stopThread(){
        flag= false;
        interrupt();
    }
}
