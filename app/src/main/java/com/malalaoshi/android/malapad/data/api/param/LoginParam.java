package com.malalaoshi.android.malapad.data.api.param;

/**
 * Created by kang on 16/12/27.
 */

public class LoginParam {
    private String phone;
    boolean test;

    public LoginParam(String phone) {
        this.phone = phone;
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
