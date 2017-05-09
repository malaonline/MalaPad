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
    @SerializedName("lc_timeslot_id")
    private int lcTimeslotId;
    public User() {
    }

    public User(String token, String phone, String name, String userId, String role, String school, Long schoolId, ClassRoom classRoom, int lcTimeslotId) {
        super(token, phone, name, userId, role);
        this.school = school;
        this.schoolId = schoolId;
        this.classRoom = classRoom;
        this.lcTimeslotId = lcTimeslotId;
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

    public int getLcTimeslotId() {
        return lcTimeslotId;
    }

    public void setLcTimeslotId(int lcTimeslotId) {
        this.lcTimeslotId = lcTimeslotId;
    }

    @Override
    public String toString() {
        return "User{" +
                "school='" + school + '\'' +
                ", schoolId=" + schoolId +
                ", classRoom=" + classRoom +
                ", lcTimeslotId=" + lcTimeslotId +
                '}';
    }
}
