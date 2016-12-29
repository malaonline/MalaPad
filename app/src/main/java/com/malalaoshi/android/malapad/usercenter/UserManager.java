package com.malalaoshi.android.malapad.usercenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.malalaoshi.android.core.AppContext;
import com.malalaoshi.android.core.entity.AuthUser;
import com.malalaoshi.android.malapad.data.entity.User;

/**
 * Created by kang on 16/12/21.
 */

public class UserManager {
    /**
     * 登录成功
     */
    public static final String ACTION_LOGINED = "com.malalaoshi.android.account.ACTION_LOGINED";

    /**
     * 登出
     */
    public static final String ACTION_LOGOUT = "com.malalaoshi.android.account.ACTION_LOGOUT";

    private static UserManager instance = new UserManager();
    // 用户信息
    private String token;
    private String userId;
    private String phoneNo;
    private String role;
    private String name;
    private String school;
    private String schoolId;
    private String lessonId;

    private UserManager() {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        token = userInfo.getString("token", "");
        userId = userInfo.getString("userId", "");
        phoneNo = userInfo.getString("phoneNo", "");
        role = userInfo.getString("role", "");

        name = userInfo.getString("name", "");
        school = userInfo.getString("school", "");
        schoolId = userInfo.getString("schoolId", "");
        lessonId = userInfo.getString("lessonId", "");
    }

    public static UserManager getInstance() {
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putString("token", token).apply();
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putString("userId", userId).apply();
        this.userId = userId;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putString("phoneNo", phoneNo).apply();
        this.phoneNo = phoneNo;
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(token);
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putString("role", role).apply();
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putString("name", name).apply();
        this.name = name;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putString("school", school).apply();
        this.school = school;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putString("schoolId", schoolId).apply();
        this.schoolId = schoolId;
    }

    public String getLessonId() {
        return lessonId;
    }

    public void setLessonId(String lessonId) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putString("lessonId", lessonId).apply();
        this.lessonId = lessonId;
    }

    public void logout() {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        token = "";
        userInfo.edit().putString("token", "").apply();
        userId = "";
        userInfo.edit().putString("userId", "").apply();
        phoneNo = "";
        userInfo.edit().putString("phoneNo", "").apply();
        role = "";
        userInfo.edit().putString("role", "").apply();
        name = "";
        userInfo.edit().putString("studname", "").apply();
        school = "";
        userInfo.edit().putString("school", school).apply();
        schoolId = "";
        userInfo.edit().putString("schoolId", schoolId).apply();
        lessonId = "";
        userInfo.edit().putString("lessonId", lessonId).apply();
        //Login success broadcast
        Intent intent = new Intent(ACTION_LOGOUT);
        AppContext.getLocalBroadcastManager().sendBroadcast(intent);
        //发送退出通知
        //EventBus.getDefault().post(new BusEvent(BusEvent.BUS_EVENT_LOGOUT_SUCCESS));
    }

    /**
     * Success login, Update login info
     *
     * @param user Login user
     */
    public void login(User user) {
        setToken(user.getToken());
        setUserId(user.getUserId()+"");
        setName(user.getName());
        setPhoneNo(user.getPhone());
        setRole(user.getRole());
        setSchool(user.getSchool());
        setSchoolId(user.getSchoolId()+"");
        setLessonId(user.getLessonId()+"");
        //Login success broadcast
        Intent intent = new Intent(ACTION_LOGINED);
        AppContext.getLocalBroadcastManager().sendBroadcast(intent);
        //发送登录成功通知
       // EventBus.getDefault().post(new BusEvent(BusEvent.BUS_EVENT_LOGIN_SUCCESS));
    }

}
