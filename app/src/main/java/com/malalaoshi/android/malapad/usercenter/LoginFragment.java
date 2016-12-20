package com.malalaoshi.android.malapad.usercenter;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.malalaoshi.android.core.AppContext;
import com.malalaoshi.android.core.base.BaseFragment;
import com.malalaoshi.android.core.utils.MiscUtil;
import com.malalaoshi.android.malapad.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kang on 16/12/20.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {

    @BindView(R.id.edit_phone)
    EditText editPhone;

    @BindView(R.id.tv_login)
    TextView tvLogin;

    private LoginContract.Presenter mPresenter;

    public static LoginFragment newInstance() {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,root);

        return root;
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick(R.id.tv_login)
    void OnClickLogin(View view) {
        Log.e("LoginFragment","phone");
        String phone = editPhone.getText().toString();
        Log.e("LoginFragment","phone");
        mPresenter.loginTask(phone);
    }

    @Override
    public void showErrorNumbleView() {
        Log.e("LoginFragment","非法手机号码");
        MiscUtil.toast("非法手机号码");
    }

    @Override
    public void showLoginingView() {

    }

    @Override
    public void showLoginComplView() {

    }
}
