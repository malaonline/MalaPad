package com.malalaoshi.android.malapad.classexercises;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.malalaoshi.android.core.base.BaseFragment;
import com.malalaoshi.android.malapad.R;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesFragment extends BaseFragment implements ExercisesContract.View, View.OnClickListener {

    @BindView(R.id.ll_user_info)
    LinearLayout llUserInfo;

    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;

    @BindView(R.id.iv_logout)
    ImageView ivLogout;

    @BindView(R.id.listview_questions)
    ListView listViewQuestions;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @BindString(R.string.user_info)
    String strUserInfo;

    private ExercisesContract.Presenter mPresenter;

    public static ExercisesFragment newInstance() {
        return new ExercisesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_exercises,container,false);
        ButterKnife.bind(this,root);
        init();
        initViews();
        initDate();
        setEvent();
        return root;
    }

    private void init() {

    }
    private void initViews() {
    }

    private void initDate() {
        tvUserInfo.setText(String.format(strUserInfo,"正通创意中心","马斯克"));
    }

    private void setEvent() {
        ivLogout.setOnClickListener(this);
    }



    @Override
    public void onStarted() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess() {

    }

    @Override
    public void onFinished() {

    }

    @Override
    public void setPresenter(ExercisesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @Override
    public void onClick(View v) {

    }
}
