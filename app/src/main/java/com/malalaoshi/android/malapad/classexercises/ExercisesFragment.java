package com.malalaoshi.android.malapad.classexercises;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.malalaoshi.android.core.base.BaseFragment;
import com.malalaoshi.android.core.utils.EmptyUtils;
import com.malalaoshi.android.malapad.R;
import com.malalaoshi.android.malapad.classexercises.adapter.QuestionAdapter;
import com.malalaoshi.android.malapad.data.entity.Option;
import com.malalaoshi.android.malapad.data.entity.ChoiceQuestion;
import com.malalaoshi.android.malapad.data.entity.QuestionGroup;
import com.malalaoshi.android.malapad.event.QuestionBusEvent;
import com.malalaoshi.android.malapad.usercenter.UserManager;
import com.malalaoshi.android.malapad.usercenter.login.LoginActivity;
import com.malalaoshi.comm.utils.DialogUtils;
import com.malalaoshi.comm.views.ScrollListView;
import com.malalaoshi.comm.views.dialogs.PromptDialog;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by kang on 16/12/26.
 */

public class ExercisesFragment extends BaseFragment implements ExercisesContract.View, View.OnClickListener {
    private static String TAG = "ExercisesFragment";

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

    private List<ChoiceQuestion> currentQuestions;

    private ExercisesContract.Presenter mPresenter;

    private Long currentGroupId = null;

    public static ExercisesFragment newInstance() {
        return new ExercisesFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_exercises,container,false);
        ButterKnife.bind(this,root);
        initDate();
        setEvent();
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
    public void setPresenter(ExercisesContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick(R.id.iv_logout)
    public void onClickLogout(View view){
        //有内存泄漏风险
        PromptDialog dialog = DialogUtils.createDoubleButtonPromptDialog(R.drawable.ic_logout
                , "是否确定退出账号~",
                "取消",
                "确定",
                new PromptDialog.OnCloseListener() {
                    @Override
                    public void onLeftClick() {
                        deleteDialog();
                    }

                    @Override
                    public void onRightClick() {
                        logout();
                        deleteDialog();
                    }
                }
                , true, true);
        if (isResumed()) {
            showDialog(dialog);
        } else {
            addDialog(dialog);
        }
    }

    private void logout() {
        //1、清除本地认证信息
        //2、跳转至登录页面
        UserManager.getInstance().userLogout();
        launchLogoutActivity();
    }

    private void launchLogoutActivity(){
        LoginActivity.launch(getContext());
        getActivity().finish();
    }

    @Override
    public void onClick(View v) {
       /* Map<String,Option> mapSelected = mQuestionAdapter.getSelectedOptions();
        if (currentQuestions.size()!=mapSelected.size()){
            showPromptDialog("题目还没有答完");
        }
        mPresenter.submitAnswerTask(mapSelected);*/
    }

    private void showPromptDialog(String message) {
        //
        PromptDialog dialog = DialogUtils.createPromptDialog(R.drawable.ic_unanswer
                , message,
                "确 定",
                new PromptDialog.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        deleteDialog();
                    }
                }
                , true, true);
        if (isResumed()) {
            showDialog(dialog);
        } else {
            addDialog(dialog);
        }
    }

    private void showLogoutDialog(String message) {
        //
        PromptDialog dialog = DialogUtils.createPromptDialog(R.drawable.ic_offline
                , message,
                "确 定",
                new PromptDialog.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        deleteDialog();
                        launchLogoutActivity();
                    }
                }
                , false, false);
        if (isResumed()) {
            showDialog(dialog);
        } else {
            addDialog(dialog);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onQuestionEvent(QuestionBusEvent busEvent) {
        Log.e(TAG,busEvent.toString());
        //if (currentGroupId==null&&currentGroupId!=busEvent.getGroudId()){
            currentGroupId = busEvent.getGroudId();
            mPresenter.loadQuestionsTask(currentGroupId);
       // }
    }

    //自动退出
    @Override
    protected void onTokenInvalid() {
        super.onTokenInvalid();
        launchLogoutActivity();
    }

    //账号被踢下线
    @Override
    protected void onLogoutSuccess() {
        super.onLogoutSuccess();
        showLogoutDialog("当前账号已在别处登录~");
    }

    @Override
    public void onLoadingQuestions() {

    }

    @Override
    public void onQuestionsLoadSuccess(QuestionGroup questionGroup) {
        rlQuestions.setVisibility(View.VISIBLE);
        subStatusView.setVisibility(View.GONE);
        tvQuestionsTitle.setText(questionGroup.getTitle());
        List<ChoiceQuestion> choiceQuestions = questionGroup.getQuestions();
        mQuestionAdapter = new QuestionAdapter(getContext(), choiceQuestions);
        listviewQuestions.setAdapter(mQuestionAdapter);
        mQuestionAdapter.notifyDataSetChanged();
    }

    @Override
    public void onQuestionsLoadFailed(Integer code, String msg) {

    }

    @Override
    public void onLoadQuestionComplete() {

    }
}
