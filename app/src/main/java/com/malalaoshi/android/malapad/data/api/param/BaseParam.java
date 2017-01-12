package com.malalaoshi.android.malapad.data.api.param;

import com.malalaoshi.android.core.AppContext;
import com.malalaoshi.android.malapad.usercenter.UserManager;

/**
 * Created by kang on 17/1/9.
 */

public abstract class BaseParam {
    private boolean test;
    public BaseParam() {
        if (AppContext.isDebug()){
            test = true;
        }
    }

    public boolean isTest() {
        return test;
    }

    public void setTest(boolean test) {
        this.test = test;
    }
}
