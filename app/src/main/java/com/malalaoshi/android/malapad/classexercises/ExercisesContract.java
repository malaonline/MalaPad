package com.malalaoshi.android.malapad.classexercises;

import com.malalaoshi.android.core.base.BasePresenter;
import com.malalaoshi.android.core.base.BaseView;
import com.malalaoshi.android.malapad.data.entity.Option;

import java.util.Map;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesContract {
    interface View extends BaseView<ExercisesContract.Presenter> {
        void onStarted();
        void onFailure();
        void onSuccess();
        void onFinished();
    }

    interface Presenter extends BasePresenter {
        void loadQuestionsTask(String questionsId);

        void submitAnswerTask(Map<String, Option> mapSelected);
    }
}
