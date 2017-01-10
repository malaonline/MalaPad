package com.malalaoshi.android.malapad.data.entity;

import com.google.gson.annotations.SerializedName;
import com.malalaoshi.android.core.entity.BaseEntity;

/**
 * Created by kang on 17/1/6.
 */

public class Heartbeat extends BaseEntity {
    @SerializedName("question_group")
    private QuestionGroup questionGroup;

    public QuestionGroup getQuestionGroup() {
        return questionGroup;
    }

    public void setQuestionGroup(QuestionGroup questionGroup) {
        this.questionGroup = questionGroup;
    }
}
