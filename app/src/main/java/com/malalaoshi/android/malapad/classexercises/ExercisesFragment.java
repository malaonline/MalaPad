package com.malalaoshi.android.malapad.classexercises;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.malalaoshi.android.core.base.BaseFragment;
import com.malalaoshi.android.malapad.R;
import com.malalaoshi.android.malapad.classexercises.adapter.QuestionAdapter;
import com.malalaoshi.android.malapad.data.entity.Answer;
import com.malalaoshi.android.malapad.data.entity.Question;
import com.malalaoshi.android.malapad.usercenter.UserManager;
import com.malalaoshi.android.malapad.usercenter.login.LoginActivity;
import com.malalaoshi.comm.views.ScrollListView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesFragment extends BaseFragment implements ExercisesContract.View, View.OnClickListener {

    @BindView(R.id.sub_status_view)
    View subStatusView;

    @BindView(R.id.tv_user_info)
    TextView tvUserInfo;

    @BindView(R.id.iv_logout)
    ImageView ivLogout;

    @BindView(R.id.rl_questions)
    RelativeLayout rlQuestions;

    @BindView(R.id.listview_questions)
    ScrollListView listviewQuestions;

    @BindView(R.id.tv_questions_title)
    TextView tvQuestionsTitle;

    @BindView(R.id.tv_submit)
    TextView tvSubmit;

    @BindString(R.string.user_info)
    String strUserInfo;

    private QuestionAdapter mQuestionAdapter;

    private ExercisesContract.Presenter mPresenter;

    public static ExercisesFragment newInstance() {
        return new ExercisesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_exercises,container,false);
        ButterKnife.bind(this,root);
        initDate();
        setEvent();root.postDelayed(new Runnable() {
            @Override
            public void run() {
                //mPresenter.loadQuestionsTask("001");
                onSuccess();
            }
        },3000);
        return root;
    }

    private void initDate() {
        UserManager userManager = UserManager.getInstance();
        tvUserInfo.setText(String.format(strUserInfo,userManager.getSchool(),userManager.getName()));
    }

    private void setEvent() {
        tvSubmit.setOnClickListener(this);
    }

    @Override
    public void onStarted() {

    }

    @Override
    public void onFailure() {

    }

    @Override
    public void onSuccess() {
        rlQuestions.setVisibility(View.VISIBLE);
        subStatusView.setVisibility(View.GONE);
        tvQuestionsTitle.setText("这是一组英语题");
        List<Question> list = new ArrayList<>();
        List<Answer> answers = new ArrayList<Answer>();
        answers.add(new Answer("001","Both",true));
        answers.add(new Answer("002","Neither",false));
        answers.add(new Answer("003","None",false));
        answers.add(new Answer("004","Either",false));
        list.add(new Question("--Which would you like, a cup of tea or a glass of milk?\n" + "--- ______. I think I’ll just have a glass of water.",answers));

        answers = null;
        answers = new ArrayList<Answer>();
        answers.add(new Answer("001","such an exciting",true));
        answers.add(new Answer("002","so an exciting",false));
        answers.add(new Answer("003","such an excited",false));
        answers.add(new Answer("004","so an excited",false));
        list.add(new Question("I’ve never seen ______ match before.",answers));

        answers = null;
        answers = new ArrayList<Answer>();
        answers.add(new Answer("001","tell",true));
        answers.add(new Answer("002","talk",false));
        answers.add(new Answer("003","speak",false));
        answers.add(new Answer("004","say",false));
        list.add(new Question("We usually _____ hello to each other",answers));

        answers = null;
        answers = new ArrayList<Answer>();
        answers.add(new Answer("001","are, is",true));
        answers.add(new Answer("002","are, are",false));
        answers.add(new Answer("003","is, are",false));
        answers.add(new Answer("004","is, is",false));
        list.add(new Question("There _____ a great number of students over there. The number of the students ____ five thousand.",answers));

        answers = null;
        answers = new ArrayList<Answer>();
        answers.add(new Answer("001","are used to take a walk, am used to swim",true));
        answers.add(new Answer("002","are used to taking a walk, am used to swimming",false));
        answers.add(new Answer("003","used to take a walk, used to swim",false));
        answers.add(new Answer("004","used to take a walk, am used to swimming",false));
        list.add(new Question("—Can you remember this park? We _____ here.—Sure. But now I ______ in that swimming pool.\n",answers));

        answers = null;
        answers = new ArrayList<Answer>();
        answers.add(new Answer("001","wish",true));
        answers.add(new Answer("002","to wish",false));
        answers.add(new Answer("003","hope",false));
        answers.add(new Answer("004","to hope",false));
        list.add(new Question("The Chinese ping-pong players will join in the match. Let's _____ them success.",answers));
        mQuestionAdapter = new QuestionAdapter(getContext(),list);
        listviewQuestions.setAdapter(mQuestionAdapter);
        mQuestionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onFinished() {

    }

    @Override
    public void setPresenter(ExercisesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick(R.id.iv_logout)
    public void onClickLogout(View view){
        //1、清除本地认证信息
        //2、跳转至登录页面
        UserManager.getInstance().logout();
        LoginActivity.launch(getContext());
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
        List<Question> questionList = mQuestionAdapter.getSelectedItems();
        mPresenter.submitAnswerTask(questionList);
    }
}
