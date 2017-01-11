package com.malalaoshi.android.core.event;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by kang on 17/1/6.
 */

public class EventDispatcher {

    private static EventDispatcher instance = new EventDispatcher();
    public static EventDispatcher getInstance(){
        return instance;
    }

    private EventDispatcher(){

    }

    public void post(BusEvent event){
        EventBus.getDefault().post(event);
    }

    public void register(Object obj) {
        EventBus.getDefault().register(obj);
    }

    public void unregister(Object obj) {
        EventBus.getDefault().unregister(obj);
    }
}
