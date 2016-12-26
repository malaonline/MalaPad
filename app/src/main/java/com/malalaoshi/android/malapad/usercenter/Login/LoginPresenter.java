package com.malalaoshi.android.malapad.usercenter.login;

import android.support.annotation.NonNull;

import com.malalaoshi.android.core.utils.EmptyUtils;
import com.malalaoshi.android.core.utils.MiscUtil;
import com.malalaoshi.android.malapad.data.TasksRepository;

import rx.subscriptions.CompositeSubscription;

/**
 * Created by kang on 16/12/20.
 */

public class LoginPresenter implements LoginContract.Presenter {

    @NonNull
    private final TasksRepository mTasksRepository;

    @NonNull
    private final LoginContract.View mLoginView;

    @NonNull
    private CompositeSubscription mSubscriptions;

    public LoginPresenter(@NonNull TasksRepository tasksRepository, @NonNull LoginContract.View loginView){
        this.mTasksRepository = tasksRepository;
        this.mLoginView = loginView;
        mLoginView.setPresenter(this);
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public void loginTask(String phone) {
        if (EmptyUtils.isEmpty(phone)||!MiscUtil.isMobilePhone(phone)) {
            mLoginView.onFailureLogin();
            mLoginView.onFinishedLogin();
            return;
        }
        mLoginView.onStartedLogin();
        /*mLoginView.showLogining();
        mSubscriptions.add(mTasksRepository
                .getTask(mTaskId)
                .subscribeOn(mSchedulerProvider.computation())
                .observeOn(mSchedulerProvider.ui())
                .subscribe(
                        // onNext
                        this::showTask,
                        // onError
                        throwable -> {
                        },
                        // onCompleted
                        () -> mTaskDetailView.setLoadingIndicator(false)));*/
    }
}
