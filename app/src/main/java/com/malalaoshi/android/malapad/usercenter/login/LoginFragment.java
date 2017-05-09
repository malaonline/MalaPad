package com.malalaoshi.android.malapad.usercenter.login;

import android.graphics.Rect;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.malalaoshi.android.core.base.BaseFragment;
import com.malalaoshi.android.core.base.BasePresenter;
import com.malalaoshi.android.core.utils.MiscUtil;
import com.malalaoshi.android.malapad.R;
import com.malalaoshi.android.malapad.classexercises.ExercisesActivity;
import com.malalaoshi.android.malapad.data.api.LoginApi;

import butterknife.BindColor;
import butterknife.BindDrawable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by kang on 16/12/20.
 */

public class LoginFragment extends BaseFragment implements LoginContract.View {

    private static final String TAG = "LoginFragment";
    @BindView(R.id.edit_phone)
    EditText editPhone;

    @BindView(R.id.tv_login)
    TextView tvLogin;

    @BindView(R.id.rl_login_card)
    RelativeLayout rlLoginCard;

    @BindView(R.id.iv_login_logo)
    ImageView ivLoginLogo;

    @BindView(R.id.tv_login_tip)
    TextView tvLoginTip;

    @BindColor(R.color.sepia_alpha)
    int colorSepiaAlpha;

    @BindColor(R.color.sepia)
    int colorSepia;

    @BindColor(R.color.red)
    int colorRed;

    @BindColor(R.color.white)
    int colorWhite;

    @BindDrawable(R.drawable.ic_login_tip)
    Drawable drawableLoginTip;

    @BindDrawable(R.drawable.ic_login_logo)
    Drawable drawableNormalLogin;

    @BindDrawable(R.drawable.ic_login_failed)
    Drawable drawableFailedLogin;

    private AnimationDrawable mAnimationDrawable;

    private com.malalaoshi.android.malapad.usercenter.login.LoginContract.Presenter mPresenter;

    public static LoginFragment newInstance() {
        return new LoginFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_login, container, false);
        ButterKnife.bind(this,root);
        init();
        initViews();
        setEvent();
        controlKeyboardLayout(root,rlLoginCard);
        return root;
    }

    private void initViews() {
        mAnimationDrawable = (AnimationDrawable) ivLoginLogo.getDrawable();
    }

    private void setEvent() {
        setLoginEnabled(false);

        editPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length()==11){
                    setLoginEnabled(true);
                }else{
                    setLoginEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void setLoginEnabled(boolean enabled){
        tvLogin.setEnabled(enabled);
        if (enabled){
            tvLogin.setTextColor(colorSepia);
        }else{
            tvLogin.setTextColor(colorSepiaAlpha);
        }
    }

    /**
     * @param root
     *            最外层布局，需要调整的布局
     * @param scrollToView
     *            被键盘遮挡的scrollToView，滚动root,使scrollToView在root可视区域的底部
     */
    private void controlKeyboardLayout(final View root, final View scrollToView) {
        // 注册一个回调函数，当在一个视图树中全局布局发生改变或者视图树中的某个视图的可视状态发生改变时调用这个回调函数。
        root.getViewTreeObserver().addOnGlobalLayoutListener(
                () -> {
                    Rect rect = new Rect();
                    // 获取root在窗体的可视区域
                    root.getWindowVisibleDisplayFrame(rect);
                    // 当前视图最外层的高度减去现在所看到的视图的最底部的y坐标
                    int rootInvisibleHeight = root.getRootView()
                            .getHeight() - rect.bottom;
                    Log.i("tag", "最外层的高度" + root.getRootView().getHeight());
                    // 若rootInvisibleHeight高度大于100，则说明当前视图上移了，说明软键盘弹出了
                    if (rootInvisibleHeight > 100) {
                        //软键盘弹出来的时候
                        int[] location = new int[2];
                        // 获取scrollToView在窗体的坐标
                        scrollToView.getLocationInWindow(location);
                        // 计算root滚动高度，使scrollToView在可见区域的底部
                        int srollHeight = (location[1] + scrollToView
                                .getHeight()) - rect.bottom;
                        root.scrollTo(0, srollHeight);
                    } else {
                        // 软键盘没有弹出来的时候
                        root.scrollTo(0, 0);
                    }
                });
    }
    private void init() {

    }

    @Override
    public void setPresenter(com.malalaoshi.android.malapad.usercenter.login.LoginContract.Presenter presenter) {
        this.mPresenter = presenter;
    }

    @OnClick(R.id.tv_login)
    void OnClickLogin(View view) {
        String phone = editPhone.getText().toString();
        mPresenter.loginTask(phone);
//        onLoginSuccess();
    }


    @Override
    public void onStartedLogin() {
        setLoginEnabled(false);
        editPhone.setEnabled(false);

        ivLoginLogo.setImageDrawable(mAnimationDrawable);
        mAnimationDrawable.start();
        tvLoginTip.setBackground(null);
        tvLoginTip.setTextColor(colorWhite);
        tvLoginTip.setText("登录中...");
    }

    @Override
    public void onLoginFailed(int code , String msg) {
        mAnimationDrawable.stop();
        ivLoginLogo.setImageDrawable(drawableFailedLogin);
        tvLoginTip.setBackground(null);
        tvLoginTip.setTextColor(colorRed);
//        Log.d(TAG,"login fialed:code="+code+",msg="+msg);
        if (code== LoginApi.ErrorCode.ERROR_CODE_BAD_NET){
            msg = "网络请求失败";
        }else if(code== LoginApi.ErrorCode.ERROR_CODE_ILLEGAL_PHONE){
            msg = "手机号码有误 请重新输入";
        }else if(code== LoginApi.ErrorCode.ERROR_CODE_ILLEGAL_USER){
            msg = "此用户没有注册 ";
        }else if(code== LoginApi.ErrorCode.ERROR_CODE_NO_LESSON){
            msg = "此用户当前时间段没有课程";
        }else{
            msg = "未知错误";
        }
        tvLoginTip.setText(msg);
        MiscUtil.toast(msg);
    }

    @Override
    public void onLoginSuccess() {
        mAnimationDrawable.stop();
        ivLoginLogo.setImageDrawable(drawableNormalLogin);

        tvLoginTip.setBackground(drawableLoginTip);
        tvLoginTip.setText("");
        ExercisesActivity.launch(getContext());
        getActivity().finish();
    }

    @Override
    public void onLoginComplete() {
        setLoginEnabled(true);
        editPhone.setEnabled(true);
    }

    @Override
    protected BasePresenter getPresent() {
        return mPresenter;
    }
}
