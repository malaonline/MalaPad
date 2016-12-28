package com.malalaoshi.android.malapad.usercenter.login;

import android.support.annotation.NonNull;

import com.malalaoshi.android.core.utils.EmptyUtils;
import com.malalaoshi.android.core.utils.MiscUtil;
import com.malalaoshi.android.malapad.data.TasksRepository;
import com.malalaoshi.android.malapad.data.api.LoginApi;
import com.malalaoshi.android.malapad.data.api.param.LoginParam;
import com.malalaoshi.android.malapad.data.entity.User;
import com.malalaoshi.android.malapad.usercenter.UserManager;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
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

    public LoginPresenter( @NonNull LoginContract.View loginView){
        this.mTasksRepository = new TasksRepository();//tasksRepository;  @NonNull TasksRepository tasksRepository,
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
            mLoginView.onFailureLogin(LoginApi.ErrorCode.ERROR_CODE_ILLEGAL_PHONE,"手机号有误，请重新输入");
            mLoginView.onFinishedLogin();
            return;
        }
        mSubscriptions.add(LoginApi.login(new LoginParam("send",phone))
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(
                        ()->{
                            mLoginView.onStartedLogin();
                        }
                )
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        user -> {
                            user.setToken("token");
                            user.setUserId("u007");
                            user.setSchool("清华大学");
                            user.setRole("stuRole");
                            user.setName("张小龙");
                            user.setPhone("110");
                            user.setSchoolId("qhdx");
                            UserManager userManager = UserManager.getInstance();
                            userManager.login(user);
                            mLoginView.onSuccessLogin();
                        },
                        throwable -> {
                            mLoginView.onFailureLogin(LoginApi.ErrorCode.ERROR_CODE_BAD_NET,"网络请求失败");
                            mLoginView.onFinishedLogin();
                        }
                        ,
                        () -> {
                            mLoginView.onFinishedLogin();
                        }
                ));
    }
}
