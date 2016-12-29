package com.malalaoshi.android.malapad.data.api.param;

/**
 * Created by kang on 16/12/27.
 */

public class LoginParam {
    String action;
    String phone;

    public LoginParam(String action, String phone) {
        this.action = action;
        this.phone = phone;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
