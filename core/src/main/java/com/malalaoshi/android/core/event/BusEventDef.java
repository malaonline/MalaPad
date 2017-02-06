package com.malalaoshi.android.core.event;

/**
 * Created by kang on 17/1/6.
 */

public class BusEventDef {
    public static final int BUS_EVENT_LOGIN_SUCCESS  = 0x01;       //登录成功后消息
    public static final int BUS_EVENT_AUTO_LOGOUT_SUCCESS = 0x02;  //服务端自动退出登录通知
    public static final int BUS_EVENT_TOKEN_INVALID  = 0x03;       //token失效（目前只涉及互踢操作）
    public static final int BUS_EVENT_USER_LOGOUT_SUCCESS = 0x04;  //用户本地主动退出
}
