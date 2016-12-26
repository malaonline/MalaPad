package com.malalaoshi.android.malapad.classexercises;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.malalaoshi.android.core.base.BaseActivity;
import com.malalaoshi.android.core.utils.ActivityUtils;
import com.malalaoshi.android.malapad.R;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesActivity extends BaseActivity {

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
