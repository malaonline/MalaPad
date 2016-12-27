package com.malalaoshi.android.malapad.classexercises;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.malalaoshi.android.core.base.BaseActivity;
import com.malalaoshi.android.core.utils.ActivityUtils;
import com.malalaoshi.android.malapad.R;
import com.malalaoshi.android.malapad.usercenter.login.LoginActivity;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesActivity extends BaseActivity {

    public static void launch(Context context) {
        Intent intent = new Intent(context,ExercisesActivity.class);
        context.startActivity(intent);
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_frame);
        ExercisesFragment exercisesFragment = (ExercisesFragment) getSupportFragmentManager().findFragmentById(R.id.container);
        if (exercisesFragment==null){
            exercisesFragment = ExercisesFragment.newInstance();
            ActivityUtils.addFragmentToActivity(getSupportFragmentManager(),exercisesFragment,R.id.container);
        }
        new ExercisesPresenter(exercisesFragment);

    }
}
