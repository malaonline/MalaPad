package com.malalaoshi.android.malapad.classexercises;

import com.malalaoshi.android.malapad.data.entity.Option;

import java.util.Map;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesPresenter implements ExercisesContract.Presenter {
    private ExercisesContract.View mExercisesView;

    public ExercisesPresenter(ExercisesContract.View view) {
        this.mExercisesView = view;
        mExercisesView.setPresenter(this);
    }

    @Override
    public void loadQuestionsTask(String questionsId) {

    }

    @Override
    public void submitAnswerTask(Map<String, Option> questionList) {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
