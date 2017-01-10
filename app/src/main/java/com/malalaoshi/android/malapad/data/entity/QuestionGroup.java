package com.malalaoshi.android.malapad.data.entity;

import com.malalaoshi.android.core.entity.BaseEntity;

import java.util.List;

/**
 * Created by kang on 17/1/10.
 */

public class QuestionGroup extends BaseEntity {
    private String title;
    private List<ChoiceQuestion> questions;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ChoiceQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(List<ChoiceQuestion> questions) {
        this.questions = questions;
    }
}
