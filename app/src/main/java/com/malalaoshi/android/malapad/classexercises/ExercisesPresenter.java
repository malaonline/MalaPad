package com.malalaoshi.android.malapad.classexercises;

import android.support.annotation.NonNull;
import android.util.Log;

import com.malalaoshi.android.malapad.data.api.ExercisesApi;
import com.malalaoshi.android.malapad.data.api.param.QuestionsParam;
import com.malalaoshi.android.malapad.data.api.response.QuestionsResponse;
import com.malalaoshi.android.malapad.data.entity.Option;
import com.malalaoshi.android.malapad.data.entity.QuestionGroup;

import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesPresenter implements ExercisesContract.Presenter {
    private ExercisesContract.View mView;
    @NonNull
    private CompositeSubscription mSubscriptions;
    public ExercisesPresenter(ExercisesContract.View view) {
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
        mSubscriptions.add(/*ExercisesApi.loadQuestions(new QuestionsParam(questionsId))*/
                observable.subscribeOn(Schedulers.io())
                .doOnSubscribe(
                        ()->{
                            mView.onLoadingQuestions();
                        }
                )
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            Log.e("api",response.getCode()+"  "+response.getMsg());
                            if (response.getCode()==0){
                                QuestionGroup questionGroup = response.getData();
                                mView.onQuestionsLoadSuccess(questionGroup);
                            }else{
                                mView.onQuestionsLoadFailed(response.getCode(),response.getMsg());
                            }
                        },
                        throwable -> {
                            mView.onQuestionsLoadFailed(1,"网络请求失败");
                            mView.onLoadQuestionComplete();
                        }
                        ,
                        () -> {
                            mView.onLoadQuestionComplete();
                        }
                ));
    }

    @Override
    public void submitAnswerTask(Map<String, Option> questionList) {
        /*if (EmptyUtils.isEmpty(phone)||!MiscUtil.isMobilePhone(phone)) {
            mLoginView.onFailureLogin(LoginApi.ErrorCode.ERROR_CODE_ILLEGAL_PHONE,"手机号有误，请重新输入");
            mLoginView.onFinishedLogin();
            return;
        }
        mSubscriptions.add(LoginApi.login(new LoginParam(phone))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(
                        ()->{
                            mLoginView.onStartedLogin();
                        }
                )
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            Log.e("api",response.getCode()+"  "+response.getMsg());
                            if (response.getCode()==0){
                                mLoginView.onSuccessLogin();
                                User user = response.getData();
                                UserManager userManager = UserManager.getInstance();
                                userManager.login(user);
                                //userManager.startHeartbeatThread();
                            }else{
                                mLoginView.onFailureLogin(response.getCode(),response.getMsg());
                            }
                        },
                        throwable -> {
                            mLoginView.onFailureLogin(LoginApi.ErrorCode.ERROR_CODE_BAD_NET,"网络请求失败");
                            mLoginView.onFinishedLogin();
                        }
                        ,
                        () -> {
                            mLoginView.onFinishedLogin();
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
