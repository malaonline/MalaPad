package com.malalaoshi.android.malapad.data.api.param;

/**
 * Created by kang on 17/1/6.
 */

public class HeartbeatParam extends BaseParam{
    private String lc_timeslot;
    public HeartbeatParam(String lc_timeslot) {
        this.lc_timeslot = lc_timeslot;
    }

    public String getLive_class() {
        return lc_timeslot;
    }

    public void setLive_class(String lc_timeslot) {
        this.lc_timeslot = lc_timeslot;
    }
}
