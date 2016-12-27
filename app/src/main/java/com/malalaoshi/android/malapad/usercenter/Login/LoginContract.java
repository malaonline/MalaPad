package com.malalaoshi.android.malapad.usercenter.login;

import com.malalaoshi.android.core.base.BasePresenter;
import com.malalaoshi.android.core.base.BaseView;

/**
 * Created by kang on 16/12/20.
 */

public class LoginContract {
    interface View extends BaseView<Presenter> {
        void onStartedLogin();
        void onFailureLogin();
        void onSuccessLogin();
        void onFinishedLogin();
    }

    interface Presenter extends BasePresenter {
        void loginTask(String number);
    }
}
