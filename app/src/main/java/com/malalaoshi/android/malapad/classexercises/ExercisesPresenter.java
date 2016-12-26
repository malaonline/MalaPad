package com.malalaoshi.android.malapad.classexercises;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesPresenter implements ExercisesContract.Presenter {
    private ExercisesContract.View mExercisesView;

    public ExercisesPresenter(ExercisesContract.View view) {
        this.mExercisesView = view;
    }

    @Override
    public void openTask(String questionsId) {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
