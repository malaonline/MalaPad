package com.malalaoshi.android.malapad.data.entity;

import java.util.List;

/**
 * Created by kang on 16/12/28.
 */

public class ChoiceQuestion {
    private String question;
    private Long answerId;
    private String answerDesc;
    private List<Option> optionList;

    public ChoiceQuestion(String question, Long answerId, String answerDesc, List<Option> optionList) {
        this.question = question;
        this.answerId = answerId;
        this.answerDesc = answerDesc;
        this.optionList = optionList;
    }

    public ChoiceQuestion(String question, List<Option> optionList) {
        this.question = question;
        this.optionList = optionList;
    }
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Option> getOptionList() {
        return optionList;
    }

    public void setOptionList(List<Option> optionList) {
        this.optionList = optionList;
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
