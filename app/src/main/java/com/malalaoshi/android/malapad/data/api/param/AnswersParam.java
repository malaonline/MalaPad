package com.malalaoshi.android.malapad.data.api.param;

import com.malalaoshi.android.malapad.data.api.entity.Answer;

import java.util.List;

/**
 * Created by kang on 17/1/11.
 */

public class AnswersParam extends BaseParam {
    private Long id;
    private List<Answer> questions;

    public AnswersParam(Long id, List<Answer> questions) {
        this.id = id;
        this.questions = questions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Answer> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Answer> questions) {
        this.questions = questions;
    }
}
