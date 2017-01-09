package com.malalaoshi.android.malapad.data.api.param;

import com.malalaoshi.android.core.AppContext;

/**
 * Created by kang on 17/1/6.
 */

public class HeartbeatParam extends BaseParam{
    private String live_class;
    public HeartbeatParam(String live_class) {
        this.live_class = live_class;
    }

    public String getLive_class() {
        return live_class;
    }

    public void setLive_class(String live_class) {
        this.live_class = live_class;
    }
}
