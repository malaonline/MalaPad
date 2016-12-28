package com.malalaoshi.android.malapad.data.entity;

import java.util.List;

/**
 * Created by kang on 16/12/28.
 */

public class Question {
    private String question;
    private List<Answer> answers;

    public Question(String question, List<Answer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
