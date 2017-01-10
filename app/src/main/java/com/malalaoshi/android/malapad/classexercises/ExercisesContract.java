package com.malalaoshi.android.malapad.classexercises;

import com.malalaoshi.android.core.base.BasePresenter;
import com.malalaoshi.android.core.base.BaseView;
import com.malalaoshi.android.malapad.data.entity.Option;
import com.malalaoshi.android.malapad.data.entity.QuestionGroup;

import java.util.Map;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesContract {
    interface View extends BaseView<ExercisesContract.Presenter> {

        void onLoadingQuestions();

        void onQuestionsLoadSuccess(QuestionGroup questionGroup);

        void onQuestionsLoadFailed(Integer code, String msg);

        void onLoadQuestionComplete();
    }

    interface Presenter extends BasePresenter {
        void loadQuestionsTask(Long questionsId);

        void submitAnswerTask(Map<String, Option> mapSelected);
    }
}
