package com.malalaoshi.android.malapad.usercenter;

import android.support.annotation.NonNull;

import com.malalaoshi.android.core.utils.EmptyUtils;
import com.malalaoshi.android.core.utils.MiscUtil;

/**
 * Created by kang on 16/12/20.
 */

public class LoginPresenter implements LoginContract.Presenter {
    @NonNull
    private final LoginContract.View mLoginView;

    public LoginPresenter(@NonNull LoginContract.View loginView){
        this.mLoginView = loginView;
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }

    @Override
    public void loginTask(String phone) {
        if (EmptyUtils.isEmpty(phone)||!MiscUtil.isMobilePhone(phone)) {
            mLoginView.showErrorNumbleView();
            return;
        }
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
