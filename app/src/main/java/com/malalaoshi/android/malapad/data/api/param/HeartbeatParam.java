package com.malalaoshi.android.malapad.data.api.param;

/**
 * Created by kang on 17/1/6.
 */

public class HeartbeatParam {
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
