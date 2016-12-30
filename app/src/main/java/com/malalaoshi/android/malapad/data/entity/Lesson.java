package com.malalaoshi.android.malapad.data.entity;

import com.google.gson.annotations.SerializedName;
import com.malalaoshi.android.core.entity.BaseEntity;

/**
 * Created by kang on 16/12/29.
 */

public class Lesson extends BaseEntity{
    @SerializedName("course_no")
    private String lessonNo;
    private String lecturer;
    private String grade;
    private String subject;

    public Lesson() {
    }

    public Lesson(Long id, String name, String lessonNo, String lecturer, String grade, String subject) {
        super(id, name);
        this.lessonNo = lessonNo;
        this.lecturer = lecturer;
        this.grade = grade;
        this.subject = subject;
    }

    public String getLessonNo() {
        return lessonNo;
    }

    public void setLessonNo(String lessonNo) {
        this.lessonNo = lessonNo;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return super.toString()+
                "Lesson{" +
                "lessonNo='" + lessonNo + '\'' +
                ", lecturer='" + lecturer + '\'' +
                ", grade='" + grade + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
