package com.malalaoshi.android.malapad.usercenter;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;

import com.malalaoshi.android.core.base.BaseActivity;
import com.malalaoshi.android.core.utils.ActivityUtils;
import com.malalaoshi.android.malapad.R;

import butterknife.ButterKnife;

/**
 * Created by kang on 16/12/20.
 */

public class LoginActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        LoginFragment loginFragment = (LoginFragment) getSupportFragmentManager()
                .findFragmentById(R.id.container);

        if (loginFragment == null) {
            loginFragment = LoginFragment.newInstance();

            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),
                    loginFragment, R.id.container);
        }

        // Create the presenter
        new LoginPresenter(loginFragment);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
