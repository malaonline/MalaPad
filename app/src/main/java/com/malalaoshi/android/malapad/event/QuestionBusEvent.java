package com.malalaoshi.android.malapad.event;

import com.malalaoshi.android.core.event.BusEvent;

/**
 * Created by kang on 17/1/6.
 */

public class QuestionBusEvent extends BusEvent {
    private String groudId;
    public QuestionBusEvent() {
    }

    public QuestionBusEvent(int eventType, String groudId) {
        super(eventType);
        this.groudId = groudId;
    }

    public String getGroudId() {
        return groudId;
    }

    public void setGroudId(String groudId) {
        this.groudId = groudId;
    }

    @Override
    public String toString() {
        return "QuestionBusEvent{" +
                "groudId='" + groudId + '\'' +
                '}';
    }
}
