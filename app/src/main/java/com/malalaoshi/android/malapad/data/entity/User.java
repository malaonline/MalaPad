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
    @SerializedName("live_course")
    private Lesson lesson;
    public User() {
    }

    public User(String token, String phone, String name, String userId, String role, String school, Long schoolId, Lesson lesson) {
        super(token, phone, name, userId, role);
        this.school = school;
        this.schoolId = schoolId;
        this.lesson = lesson;
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

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
