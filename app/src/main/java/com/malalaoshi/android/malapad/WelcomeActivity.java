package com.malalaoshi.android.malapad;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.malalaoshi.android.core.base.BaseActivity;
import com.malalaoshi.android.malapad.classexercises.ExercisesActivity;
import com.malalaoshi.android.malapad.usercenter.UserManager;
import com.malalaoshi.android.malapad.usercenter.login.LoginActivity;

import java.lang.ref.WeakReference;

public class WelcomeActivity extends BaseActivity {

    private Handler mHandler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        mHandler.postDelayed(new MyRunnable(this),1500);
    }

    static class MyRunnable implements Runnable{
        private WeakReference<Activity> activitys;
        public MyRunnable(Activity activity){
            activitys = new WeakReference<Activity>(activity);
        }
        @Override
        public void run() {
            Activity activity = activitys.get();
            if (activity!=null){
                UserManager userManager = UserManager.getInstance();
                if (userManager.isLogin()){
                    Intent intent = new Intent(activity,ExercisesActivity.class);
                    activity.startActivity(intent);
                }else{
                    LoginActivity.launch(activity);
                }
                activity.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                activity.finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }

}
