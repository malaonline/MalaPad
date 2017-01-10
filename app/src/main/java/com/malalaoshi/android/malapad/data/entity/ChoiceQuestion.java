package com.malalaoshi.android.malapad.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by kang on 16/12/28.
 */

public class ChoiceQuestion {
    @SerializedName("title")
    private String question;
    private Long answerId;
    private String answerDesc;
    private List<Option> options;

    public ChoiceQuestion(String question, Long answerId, String answerDesc, List<Option> options) {
        this.question = question;
        this.answerId = answerId;
        this.answerDesc = answerDesc;
        this.options = options;
    }

    public ChoiceQuestion(String question, List<Option> options) {
        this.question = question;
        this.options = options;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public Long getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Long answerId) {
        this.answerId = answerId;
    }

    public String getAnswerDesc() {
        return answerDesc;
    }

    public void setAnswerDesc(String answerDesc) {
        this.answerDesc = answerDesc;
    }
}
