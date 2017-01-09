package com.malalaoshi.android.malapad.data.api.param;

import com.malalaoshi.android.core.AppContext;
import com.malalaoshi.android.malapad.usercenter.UserManager;

/**
 * Created by kang on 17/1/9.
 */

public class BaseParam {
    private String token;
    private boolean test;
    public BaseParam() {
        if (addAuthHeader()){
            token = UserManager.getInstance().getToken();
        }
        if (AppContext.isDebug()){
            test = true;
        }
    }

    protected boolean addAuthHeader() {
        return true;
    }

    public BaseParam(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
