package com.malalaoshi.android.malapad.classexercises;

import com.malalaoshi.android.core.base.BasePresenter;
import com.malalaoshi.android.core.base.BaseView;
import com.malalaoshi.android.malapad.data.api.entity.Answer;
import com.malalaoshi.android.malapad.data.api.entity.Ok;
import com.malalaoshi.android.malapad.data.entity.Option;
import com.malalaoshi.android.malapad.data.entity.QuestionGroup;

import java.util.List;
import java.util.Map;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesContract {
    interface View extends BaseView<ExercisesContract.Presenter> {

        void onStartFetchQuestions();

        void onFetchQuestionsSuccess(QuestionGroup questionGroup);

        void onFetchQuestionsFailed(Integer code, String msg);

        void onFetchQuestionComplete();

        void onStartPostAnswers();

        void onPostAnswersSuccess(Ok ok);

        void onPostAnswersFailed(Integer code, String msg);

        void onPostAnswersComplete();
    }

    interface Presenter extends BasePresenter {

        void loadQuestionsTask(Long questionsId);

        void submitAnswerTask(Long groupId, List<Answer> answers);
    }
}
