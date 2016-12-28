package com.malalaoshi.android.malapad.data.entity;

/**
 * Created by kang on 16/12/28.
 */

public class Question {
    private String question;
    private String[] answers;
    private String answer;

    private int selected;
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }
}
