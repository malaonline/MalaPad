package com.malalaoshi.android.malapad.data.api.param;

import com.malalaoshi.android.core.AppContext;

/**
 * Created by kang on 16/12/27.
 */

public class LoginParam {
    private String phone;
    boolean test;

    public LoginParam(String phone) {
        this.phone = phone;
        if (AppContext.isDebug()){
            test = true;
        }
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }
}
