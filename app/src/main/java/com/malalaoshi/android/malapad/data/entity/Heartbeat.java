package com.malalaoshi.android.malapad.data.entity;

import com.google.gson.annotations.SerializedName;
import com.malalaoshi.android.core.entity.BaseEntity;

/**
 * Created by kang on 17/1/6.
 */

public class Heartbeat extends BaseEntity {
    @SerializedName("question_group")
    private Long questionGroup;

    @SerializedName("exercise_session")
    private Long exerciseSession;

    public Long getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(Long questionGroup) {
        this.questionGroup = questionGroup;
    }

    public Long getExerciseSession() {
        return exerciseSession;
    }

    public void setExerciseSession(Long exerciseSession) {
        this.exerciseSession = exerciseSession;
    }

    @Override
    public String toString() {
        return "Heartbeat{" +
                "questionGroup=" + questionGroup +
                ", exerciseSession=" + exerciseSession +
                '}';
    }
}
