package com.malalaoshi.android.malapad.classexercises;

import com.malalaoshi.android.core.base.BasePresenter;
import com.malalaoshi.android.core.base.BaseView;

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
        void openTask(String questionsId);
    }
}
