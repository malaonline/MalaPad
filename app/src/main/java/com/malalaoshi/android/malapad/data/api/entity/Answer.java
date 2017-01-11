package com.malalaoshi.android.malapad.data.api.entity;

/**
 * Created by kang on 17/1/11.
 */

public class Answer {
    private Long questionId;
    private Long answerId;

    public Answer(Long questionId, Long answerId) {
        this.questionId = questionId;
        this.answerId = answerId;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }
}
