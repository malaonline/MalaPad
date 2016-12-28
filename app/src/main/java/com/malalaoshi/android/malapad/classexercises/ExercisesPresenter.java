package com.malalaoshi.android.malapad.classexercises;

import com.malalaoshi.android.malapad.data.entity.Question;

import java.util.List;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesPresenter implements ExercisesContract.Presenter {
    private ExercisesContract.View mExercisesView;

    public ExercisesPresenter(ExercisesContract.View view) {
        this.mExercisesView = view;
    }

    @Override
    public void loadQuestionsTask(String questionsId) {

    }

    @Override
    public void submitAnswerTask(List<Question> questionList) {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
