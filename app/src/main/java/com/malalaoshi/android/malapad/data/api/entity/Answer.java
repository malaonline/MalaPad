package com.malalaoshi.android.malapad.data.api.entity;

/**
 * Created by kang on 17/1/11.
 */

public class Answer {
    private Long question;
    private Long option;

    public Answer(Long question, Long option) {
        this.question = question;
        this.option = option;
    }

    public Long getQuestion() {
        return question;
    }

    public void setQuestion(Long question) {
        this.question = question;
    }

    public Long getOption() {
        return option;
    }

    public void setOption(Long option) {
        this.option = option;
    }
}
