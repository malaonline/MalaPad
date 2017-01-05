package com.malalaoshi.android.malapad.data.entity;

import com.google.gson.annotations.SerializedName;
import com.malalaoshi.android.core.entity.AuthUser;

/**
 * Created by kang on 16/12/28.
 */

public class User extends AuthUser {
    private String school;
    @SerializedName("school_id")
    private Long schoolId;
    @SerializedName("live_class")
    private ClassRoom classRoom;
    public User() {
    }

    public User(String token, String phone, String name, String userId, String role, String school, Long schoolId, ClassRoom classRoom) {
        super(token, phone, name, userId, role);
        this.school = school;
        this.schoolId = schoolId;
        this.classRoom = classRoom;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public Long getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(Long schoolId) {
        this.schoolId = schoolId;
    }

    public ClassRoom getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(ClassRoom classRoom) {
        this.classRoom = classRoom;
    }
}
