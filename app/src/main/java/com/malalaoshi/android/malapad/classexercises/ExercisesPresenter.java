package com.malalaoshi.android.malapad.classexercises;

import android.support.annotation.NonNull;
import android.util.Log;

import com.malalaoshi.android.core.network.api.BaseApiCallback;
import com.malalaoshi.android.malapad.data.TasksRepository;
import com.malalaoshi.android.malapad.data.api.ExercisesApi;
import com.malalaoshi.android.malapad.data.api.entity.Answer;
import com.malalaoshi.android.malapad.data.api.entity.Ok;
import com.malalaoshi.android.malapad.data.api.param.AnswersParam;
import com.malalaoshi.android.malapad.data.api.param.QuestionsParam;
import com.malalaoshi.android.malapad.data.api.response.AnswerResponse;
import com.malalaoshi.android.malapad.data.api.response.QuestionsResponse;
import com.malalaoshi.android.malapad.data.entity.QuestionGroup;

import java.util.List;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kang on 16/12/26.
 */

class ExercisesPresenter implements ExercisesContract.Presenter {
    private ExercisesContract.View mView;
    @NonNull
    private CompositeSubscription mSubscriptions;
    ExercisesPresenter(ExercisesContract.View view) {
        this.mView = view;
        mView.setPresenter(this);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void loadQuestionsTask(Long questionsId) {
        if (questionsId==null) {
            return;
        }
        Observable<QuestionsResponse> observable = ExercisesApi.loadQuestions(new QuestionsParam(questionsId));
        mSubscriptions.add(new TasksRepository<QuestionsResponse>(new BaseApiCallback<QuestionsResponse>() {
            @Override
            public void onApiStarted() {
                super.onApiStarted();
                mView.onStartFetchQuestions();
            }

            @Override
            public void onSuccess(QuestionsResponse response) {
                QuestionGroup questionGroup = response.getData();
                mView.onFetchQuestionsSuccess(questionGroup);
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                mView.onFetchQuestionsFailed(code,msg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mView.onFetchQuestionComplete();
            }
        },true).addTask(observable));

      /*


        mSubscriptions.add(*//*ExercisesApi.loadQuestions(new QuestionsParam(questionsId))*//*
                observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(
                        ()->{
                            mView.onStartFetchQuestions();
                        }
                )
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            Log.e("api",response.getCode()+"  "+response.getMsg());
                            if (response.getCode()==0){
                                QuestionGroup questionGroup = response.getData();
                                mView.onFetchQuestionsSuccess(questionGroup);
                            }else{
                                mView.onFetchQuestionsFailed(response.getCode(),response.getMsg());
                            }
                        },
                        throwable -> {
                            mView.onFetchQuestionsFailed(1,"网络请求失败");
                            mView.onFetchQuestionComplete();
                        }
                        ,
                        () -> {
                            mView.onFetchQuestionComplete();
                        }
                ));*/
    }

    @Override
    public void submitAnswerTask(Long groupId, List<Answer> answers) {
        if (groupId==null&&answers!=null) {
            return;
        }
        Observable<AnswerResponse> observable = ExercisesApi.submitAnswers(new AnswersParam(groupId,answers));
        mSubscriptions.add(new TasksRepository<AnswerResponse>(new BaseApiCallback<AnswerResponse>() {
            @Override
            public void onApiStarted() {
                super.onApiStarted();
                mView.onStartPostAnswers();
            }

            @Override
            public void onSuccess(AnswerResponse response) {
                Ok ok = response.getData();
                mView.onPostAnswersSuccess(ok);
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                mView.onPostAnswersFailed(code,msg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mView.onPostAnswersComplete();
            }
        },true).addTask(observable));
      /*
        mSubscriptions.add(*//*ExercisesApi.loadQuestions(new QuestionsParam(questionsId))*//*
                observable.subscribeOn(Schedulers.io())
                        .doOnSubscribe(
                                ()->{
                                    mView.onStartPostAnswers();
                                }
                        )
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                response -> {
                                    Log.e("api",response.getCode()+"  "+response.getMsg());
                                    if (response.getCode()==0){
                                        Ok ok = response.getData();
                                        mView.onPostAnswersSuccess(ok);
                                    }else{
                                        mView.onPostAnswersFailed(response.getCode(),response.getMsg());
                                    }
                                },
                                throwable -> {
                                    mView.onPostAnswersFailed(1,"网络请求失败");
                                    mView.onPostAnswersComplete();
                                }
                                ,
                                () -> {
                                    mView.onPostAnswersComplete();
                                }
                        ));*/
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }
}
