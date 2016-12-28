package com.malalaoshi.android.malapad.data.entity;

import com.malalaoshi.android.core.entity.AuthUser;

/**
 * Created by kang on 16/12/28.
 */

public class User extends AuthUser {
    private String school;
    private String schoolId;

    public User() {
    }

    public User(String token, String phone, String name, String userId, String role, String school, String schoolId) {
        super(token, phone, name, userId, role);
        this.school = school;
        this.schoolId = schoolId;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
