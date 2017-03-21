package com.malalaoshi.android.malapad.event;

import com.malalaoshi.android.core.event.BusEvent;

/**
 * Created by kang on 17/1/6.
 */

public class QuestionBusEvent extends BusEvent {
    private Long groudId;
    private Long sessionId;
    public QuestionBusEvent() {
    }

    public QuestionBusEvent(int eventType, Long groudId, Long sessionId) {
        super(eventType);
        this.groudId = groudId;
        this.sessionId = sessionId;
    }

    public Long getGroudId() {
        return groudId;
    }

    public void setGroudId(Long groudId) {
        this.groudId = groudId;
    }

    public Long getSessionId() {
        return sessionId;
    }

    public void setSessionId(Long sessionId) {
        this.sessionId = sessionId;
    }

    @Override
    public String toString() {
        return "QuestionBusEvent{" +
                "groudId='" + groudId + '\'' +
                '}';
    }
}
