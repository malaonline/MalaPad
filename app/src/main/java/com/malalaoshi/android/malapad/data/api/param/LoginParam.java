package com.malalaoshi.android.malapad.data.api.param;

import com.malalaoshi.android.core.AppContext;

/**
 * Created by kang on 16/12/27.
 */

public class LoginParam extends BaseParam{
    private String phone;

    public LoginParam(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
