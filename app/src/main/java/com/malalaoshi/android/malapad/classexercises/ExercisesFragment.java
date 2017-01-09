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
import com.malalaoshi.android.core.event.BusEvent;
import com.malalaoshi.android.core.event.BusEventDef;
import com.malalaoshi.android.core.event.EventDispatcher;
import com.malalaoshi.android.malapad.R;
import com.malalaoshi.android.malapad.classexercises.adapter.QuestionAdapter;
import com.malalaoshi.android.malapad.data.entity.Option;
import com.malalaoshi.android.malapad.data.entity.ChoiceQuestion;
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

    private List<ChoiceQuestion> choiceQuestionList;

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
        choiceQuestionList = new ArrayList<>();
        List<Option> options = new ArrayList<Option>();
        options.add(new Option("001","Both",false));
        options.add(new Option("002","Neither",false));
        options.add(new Option("003","None",false));
        options.add(new Option("004","Either",false));
        choiceQuestionList.add(new ChoiceQuestion("--Which would you like, a cup of tea or a glass of milk?\n" + "--- ______. I think I’ll just have a glass of water.", options));

        options = new ArrayList<Option>();
        options.add(new Option("001","such an exciting",false));
        options.add(new Option("002","so an exciting",false));
        options.add(new Option("003","such an excited",false));
        options.add(new Option("004","so an excited",false));
        choiceQuestionList.add(new ChoiceQuestion("I’ve never seen ______ match before.", options));

        options = new ArrayList<Option>();
        options.add(new Option("001","tell",false));
        options.add(new Option("002","talk",false));
        options.add(new Option("003","speak",false));
        options.add(new Option("004","say",false));
        choiceQuestionList.add(new ChoiceQuestion("We usually _____ hello to each other", options));

        options = new ArrayList<Option>();
        options.add(new Option("001","are, is",false));
        options.add(new Option("002","are, are",false));
        options.add(new Option("003","is, are",false));
        options.add(new Option("004","is, is",false));
        choiceQuestionList.add(new ChoiceQuestion("There _____ a great number of students over there. The number of the students ____ five thousand.", options));

        options = new ArrayList<Option>();
        options.add(new Option("001","are used to take a walk, am used to swim",false));
        options.add(new Option("002","are used to taking a walk, am used to swimming",false));
        options.add(new Option("003","used to take a walk, used to swim",false));
        options.add(new Option("004","used to take a walk, am used to swimming",false));
        choiceQuestionList.add(new ChoiceQuestion("—Can you remember this park? We _____ here.—Sure. But now I ______ in that swimming pool.\n", options));

        options = new ArrayList<Option>();
        options.add(new Option("001","wish",false));
        options.add(new Option("002","to wish",false));
        options.add(new Option("003","hope",false));
        options.add(new Option("004","to hope",false));
        choiceQuestionList.add(new ChoiceQuestion("The Chinese ping-pong players will join in the match. Let's _____ them success.", options));
        mQuestionAdapter = new QuestionAdapter(getContext(), choiceQuestionList);
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
        redirectLogout();
    }

    private void redirectLogout(){
        LoginActivity.launch(getContext());
        getActivity().finish();
    }



    @Override
    public void onClick(View v) {
        Map<String,Option> mapSelected = mQuestionAdapter.getSelectedOptions();
        if (choiceQuestionList.size()!=mapSelected.size()){
            showPromptDialog("题目还没有答完");
        }
        mPresenter.submitAnswerTask(mapSelected);
    }

    @Override
    public void onStart() {
        super.onStart();
        EventDispatcher.getInstance().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventDispatcher.getInstance().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(BusEvent busEvent) {
        Log.e("event",busEvent.toString());
        if (busEvent.getEventType()== BusEventDef.BUS_EVENT_LOGOUT_SUCCESS){
            //自动退出
            redirectLogout();
        }else if (busEvent.getEventType()== BusEventDef.BUS_EVENT_TOKEN_INVALID){
            //账号被踢下线//自动退出
            showLogoutDialog("当前账号已在别处登录~");
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onQuestionEvent(QuestionBusEvent busEvent) {
        Log.e("event",busEvent.toString());
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
                        redirectLogout();
                    }
                }
                , false, false);
        if (isResumed()) {
            showDialog(dialog);
        } else {
            addDialog(dialog);
        }
    }
}
