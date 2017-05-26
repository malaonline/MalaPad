package com.malalaoshi.android.malapad.usercenter;

import android.content.Intent;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.malalaoshi.android.core.AppContext;
import com.malalaoshi.android.core.event.BusEvent;
import com.malalaoshi.android.core.event.BusEventDef;
import com.malalaoshi.android.core.event.EventDispatcher;
import com.malalaoshi.android.core.utils.MiscUtil;
import com.malalaoshi.android.malapad.R;
import com.malalaoshi.android.malapad.data.entity.ClassRoom;
import com.malalaoshi.android.malapad.data.entity.Lesson;
import com.malalaoshi.android.malapad.data.entity.User;
import com.malalaoshi.android.malapad.data.heartbeat.HeartbeatThread;

import org.greenrobot.eventbus.EventBus;

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
    private ClassRoom classRoom;
    private HeartbeatThread heartbeatThread;
    private int mLcTimeslotId;

    private UserManager() {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        token = userInfo.getString("token", "");
        userId = userInfo.getString("userId", "");
        phoneNo = userInfo.getString("phoneNo", "");
        role = userInfo.getString("role", "");

        name = userInfo.getString("name", "");
        school = userInfo.getString("school", "");
        schoolId = userInfo.getString("schoolId", "");
        classRoom = getClassRoomInfo();//userInfo.getString("lesson", "");
        mLcTimeslotId = userInfo.getInt("lcTimeslotId", -1);
    }

    private ClassRoom getClassRoomInfo() {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        ClassRoom classRoom = new ClassRoom();
        classRoom.setId(userInfo.getLong("classRoomId", -1L));
        classRoom.setAssistant(userInfo.getString("classRoomAssistant", ""));
        classRoom.setClassRoom(userInfo.getString("classRoom", ""));
        classRoom.setLesson(getLessonInfo());
        return classRoom;
    }


    private void setClassRoomInfo(ClassRoom classRoom) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        if (classRoom!=null){
            userInfo.edit().putLong("classRoomId", classRoom.getId()).apply();
            userInfo.edit().putString("classRoomAssistant", classRoom.getAssistant()).apply();
            userInfo.edit().putString("classRoom", classRoom.getClassRoom()).apply();
            setLessonInfo(classRoom.getLesson());
        }else{
            clearClassRoomInfo();
        }
    }

    private void clearClassRoomInfo(){
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putLong("classRoomId", -1L).apply();
        userInfo.edit().putString("classRoomAssistant", "").apply();
        userInfo.edit().putString("classRoom", "").apply();
        clearLessonInfo();
    }
    private Lesson getLessonInfo(){
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        Lesson lesson = new Lesson();
        lesson.setId(userInfo.getLong("lessonId", -1L));
        lesson.setLessonNo(userInfo.getString("lessonNo", ""));
        lesson.setName(userInfo.getString("lessonName", ""));
        lesson.setLecturer(userInfo.getString("lessonLecturer", ""));
        lesson.setGrade(userInfo.getString("lessonGrade", ""));
        lesson.setSubject(userInfo.getString("lessonSubject", ""));
        return lesson;
    }

    private void setLessonInfo(Lesson lesson) {
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        if (classRoom!=null){
            userInfo.edit().putLong("lessonId", lesson.getId()).apply();
            userInfo.edit().putString("lessonNo", lesson.getLessonNo()).apply();
            userInfo.edit().putString("lessonName", lesson.getName()).apply();
            userInfo.edit().putString("lessonLecturer", lesson.getLecturer()).apply();
            userInfo.edit().putString("lessonGrade", lesson.getGrade()).apply();
            userInfo.edit().putString("lessonSubject", lesson.getSubject()).apply();
        }else{
            clearLessonInfo();
        }
    }

    private void clearLessonInfo(){
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putLong("lessonId", -1L).apply();
        userInfo.edit().putString("lessonNo", "").apply();
        userInfo.edit().putString("lessonName", "").apply();
        userInfo.edit().putString("lessonLecturer", "").apply();
        userInfo.edit().putString("lessonGrade", "").apply();
        userInfo.edit().putString("lessonSubject", "").apply();
    }

    public static UserManager getInstance() {
        return instance;
    }

    public synchronized String getToken() {
        return token;
    }

    public synchronized void setToken(String token) {
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

    public synchronized boolean isLogin() {
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

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        setClassRoomInfo(classRoom);
        this.classRoom = classRoom;
    }

    private synchronized void logout() {
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
        classRoom = null;
        clearClassRoomInfo();
        mLcTimeslotId = -1;
        userInfo.edit().putInt("lcTimeslotId", mLcTimeslotId).apply();
        //Login success broadcast
        Intent intent = new Intent(ACTION_LOGOUT);
        AppContext.getLocalBroadcastManager().sendBroadcast(intent);
        //stopHeartbeatThread();
    }

    public void userLogout(){
        logout();
        //发送退出通知
        EventDispatcher.getInstance().post(new BusEvent(BusEventDef.BUS_EVENT_USER_LOGOUT_SUCCESS));
        MiscUtil.toast(R.string.toast_user_lougot);
    }

    public void autoLogout(){
        logout();
        //发送退出通知
        EventDispatcher.getInstance().post(new BusEvent(BusEventDef.BUS_EVENT_AUTO_LOGOUT_SUCCESS));
        MiscUtil.toast(R.string.toast_lougot);
    }

    //token失效
    public void tokenInvalid(){
        logout();
        //发送退出通知
        EventDispatcher.getInstance().post(new BusEvent(BusEventDef.BUS_EVENT_TOKEN_INVALID));
    }


    /**
     * Success login, Update login info
     *
     * @param user Login user
     */
    public void login(User user) {
        setToken(user.getToken());
        setUserId(user.getUserId() + "");
        setName(user.getName());
        setPhoneNo(user.getPhone());
        setRole(user.getRole());
        setSchool(user.getSchool());
        setSchoolId(user.getSchoolId() + "");
        setClassRoom(user.getClassRoom());
        setLcTimeslotId(user.getLcTimeslotId());
        //Login success broadcast
        Intent intent = new Intent(ACTION_LOGINED);
        AppContext.getLocalBroadcastManager().sendBroadcast(intent);
        //发送登录成功通知
        EventBus.getDefault().post(new BusEvent(BusEventDef.BUS_EVENT_LOGIN_SUCCESS));
    }

    public void startHeartbeatThread(){
        stopHeartbeatThread();
        heartbeatThread = new HeartbeatThread();
        heartbeatThread.setName("heart beat thread");
        heartbeatThread.start();
    }

    public void stopHeartbeatThread(){
        if (heartbeatThread!=null){
            heartbeatThread.stopThread();
            heartbeatThread = null;
        }
    }

    public void setLcTimeslotId(int lcTimeslotId) {
        mLcTimeslotId = lcTimeslotId;
        SharedPreferences userInfo = AppContext.getContext().getSharedPreferences("userInfo", 0);
        userInfo.edit().putInt("lcTimeslotId", mLcTimeslotId).apply();
    }

    public int getLcTimeslotId() {
        return mLcTimeslotId;
    }
}
