package com.malalaoshi.android.malapad.usercenter.login;

import android.support.annotation.NonNull;

import com.malalaoshi.android.core.network.api.BaseApiCallback;
import com.malalaoshi.android.core.utils.EmptyUtils;
import com.malalaoshi.android.core.utils.MiscUtil;
import com.malalaoshi.android.malapad.data.TasksRepository;
import com.malalaoshi.android.malapad.data.api.LoginApi;
import com.malalaoshi.android.malapad.data.api.param.LoginParam;
import com.malalaoshi.android.malapad.data.api.response.LoginResponse;
import com.malalaoshi.android.malapad.data.entity.User;
import com.malalaoshi.android.malapad.usercenter.UserManager;

import rx.Observable;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by kang on 16/12/20.
 */

public class LoginPresenter implements LoginContract.Presenter {

    @NonNull
    private final LoginContract.View mLoginView;

    @NonNull
    private CompositeSubscription mSubscriptions;

    public LoginPresenter( @NonNull LoginContract.View loginView){
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
            mLoginView.onLoginFailed(LoginApi.ErrorCode.ERROR_CODE_ILLEGAL_PHONE,"手机号有误，请重新输入");
            mLoginView.onLoginComplete();
            return;
        }
        Observable<LoginResponse> observable = LoginApi.login(new LoginParam(phone));
        mSubscriptions.add(new TasksRepository<LoginResponse>(new BaseApiCallback<LoginResponse>() {
            @Override
            public void onApiStarted() {
                super.onApiStarted();
                mLoginView.onStartedLogin();
            }

            @Override
            public void onSuccess(LoginResponse response) {
                mLoginView.onLoginSuccess();
                User user = response.getData();
                UserManager userManager = UserManager.getInstance();
                userManager.login(user);
            }

            @Override
            public void onFailure(int code, String msg) {
                super.onFailure(code, msg);
                mLoginView.onLoginFailed(code,msg);
            }

            @Override
            public void onFinish() {
                super.onFinish();
                mLoginView.onLoginComplete();
            }
        },false).addTask(observable));
    }
}
