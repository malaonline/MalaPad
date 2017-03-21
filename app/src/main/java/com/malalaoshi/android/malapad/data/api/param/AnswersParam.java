package com.malalaoshi.android.malapad.data.api.param;

import com.malalaoshi.android.malapad.data.api.entity.Answer;

import java.util.List;

/**
 * Created by kang on 17/1/11.
 */

public class AnswersParam extends BaseParam {
    private Long id;
    private Long exercise_session;
    private List<Answer> answers;

    public AnswersParam(Long id,Long exercise_session, List<Answer> questions) {
        this.id = id;
        this.answers = questions;
        this.exercise_session = exercise_session;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getExercise_session() {
        return exercise_session;
    }

    public void setExercise_session(Long exercise_session) {
        this.exercise_session = exercise_session;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }
}
