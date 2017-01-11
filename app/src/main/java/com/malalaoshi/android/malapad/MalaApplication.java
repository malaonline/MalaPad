package com.malalaoshi.android.malapad;

import com.malalaoshi.android.core.base.BaseApplication;
import com.malalaoshi.android.malapad.usercenter.UserManager;

/**
 * Created by kang on 17/1/9.
 */

public class MalaApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        UserManager userManager = UserManager.getInstance();
        userManager.startHeartbeatThread();
    }
    @Override
    public void onTerminate() {
        super.onTerminate();
        UserManager userManager = UserManager.getInstance();
        userManager.stopHeartbeatThread();

    }
}
