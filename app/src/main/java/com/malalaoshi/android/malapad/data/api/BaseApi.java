package com.malalaoshi.android.malapad.data.api;

import com.malalaoshi.android.malapad.usercenter.UserManager;

/**
 * Created by kang on 17/1/11.
 */

public abstract class BaseApi {
    protected static String getToken(){
        return UserManager.getInstance().getToken();
    }
}
