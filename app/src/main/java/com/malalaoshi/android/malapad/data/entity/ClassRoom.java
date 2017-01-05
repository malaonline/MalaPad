package com.malalaoshi.android.malapad.data.entity;

import com.google.gson.annotations.SerializedName;
import com.malalaoshi.android.core.entity.BaseEntity;

/**
 * Created by kang on 17/1/5.
 */

public class ClassRoom extends BaseEntity {
    private String assistant;
    @SerializedName("class_room")
    private String classRoom;
    @SerializedName("live_course")
    private Lesson lesson;

    public ClassRoom() {
    }

    public ClassRoom(Long id, String name, String assistant, String classRoom, Lesson lesson) {
        super(id, name);
        this.assistant = assistant;
        this.classRoom = classRoom;
        this.lesson = lesson;
    }

    public String getAssistant() {
        return assistant;
    }

    public void setAssistant(String assistant) {
        this.assistant = assistant;
    }

    public String getClassRoom() {
        return classRoom;
    }

    public void setClassRoom(String classRoom) {
        this.classRoom = classRoom;
    }

    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }
}
