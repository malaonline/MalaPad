package com.malalaoshi.android.malapad.data.entity;

import com.malalaoshi.android.core.entity.AuthUser;

/**
 * Created by kang on 16/12/28.
 */

public class User extends AuthUser {
    private String school;
    private Long schoolId;
    private Long lessonId;
    public User() {
    }

    public User(String token, String phone, String name, String userId, String role, String school, Long schoolId, Long lessonId) {
        super(token, phone, name, userId, role);
        this.school = school;
        this.schoolId = schoolId;
        this.lessonId = lessonId;
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

    public Long getLessonId() {
        return lessonId;
    }

    public void setLessonId(Long lessonId) {
        this.lessonId = lessonId;
    }
}
