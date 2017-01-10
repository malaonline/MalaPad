package com.malalaoshi.android.core.event;

/**
 * Created by kang on 17/1/6.
 */

public class BusEvent {
    private int eventType;
    public BusEvent() {
    }

    public BusEvent(int eventType) {
        this.eventType = eventType;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    @Override
    public String toString() {
        return "BusEvent{" +
                "eventType=" + eventType +
                '}';
    }
}
