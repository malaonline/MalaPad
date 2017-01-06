package com.malalaoshi.comm.views.dialogs;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.malalaoshi.comm.R;


/**
 * Created by kang on 17/1/5.
 */

public class PromptDialog extends BaseDialog implements View.OnClickListener {
    private static String ARGS_DIALOG_TYPE_KET = "dialog type";
    private static String ARGS_MESSAGE_TEXT_KET = "message";
    private static String ARGS_LEFT_TEXT_KET = "left text";
    private static String ARGS_RIGHT_TEXT_KET = "right text";
    private static String ARGS_DRAWABLE_ID_KET = "drawable id";
    private static String ARGS_CANCEL_TEXT_KET = "cancel text";
    private static String ARGS_CANCANCEL_KET = "cancelable";
    private static String ARGS_CANBACK_KET = "can back";
    private static int ONE_BUTTON = 0;
    private static int TWO_BUTTON = 1;
    public interface OnCloseListener {
        void onLeftClick();

        void onRightClick();
    }

    public interface OnDismissListener {
        void onDismiss();
    }

    private boolean canBack = false;
    private TextView tvPromptContent;
    private TextView tvLeft;
    private TextView tvRight;
    private TextView tvBtn;
    private ImageView ivIcon;
    private LinearLayout llDoubleBtn;

    public OnDismissListener dismissListener;
    public OnCloseListener closeListener;


    public static PromptDialog newInstance(int drawableId, String message, String leftText, String rightText, boolean cancelable, boolean backable) {
        PromptDialog dialog = new PromptDialog();
        Bundle args = new Bundle();
        args.putInt(ARGS_DIALOG_TYPE_KET, TWO_BUTTON);
        args.putInt(ARGS_DRAWABLE_ID_KET, drawableId);
        args.putString(ARGS_MESSAGE_TEXT_KET,message);
        args.putString(ARGS_LEFT_TEXT_KET,leftText);
        args.putString(ARGS_RIGHT_TEXT_KET,rightText);
        args.putBoolean(ARGS_CANCANCEL_KET,cancelable);
        args.putBoolean(ARGS_CANBACK_KET,backable);
        dialog.setArguments(args);
        return dialog;
    }

    public static PromptDialog newInstance(int drawableId, String message, String btnText, boolean cancelable, boolean backable) {
        PromptDialog dialog = new PromptDialog();
        Bundle args = new Bundle();
        args.putInt(ARGS_DIALOG_TYPE_KET, ONE_BUTTON);
        args.putInt(ARGS_DRAWABLE_ID_KET, drawableId);
        args.putString(ARGS_MESSAGE_TEXT_KET,message);
        args.putString(ARGS_CANCEL_TEXT_KET,btnText);
        args.putBoolean(ARGS_CANCANCEL_KET,cancelable);
        args.putBoolean(ARGS_CANBACK_KET,backable);
        dialog.setArguments(args);
        return dialog;
    }

    public void setDismissListener(OnDismissListener dismissListener) {
        this.dismissListener = dismissListener;
    }

    public void setCloseListener(OnCloseListener closeListener) {
        this.closeListener = closeListener;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
        Bundle args = getArguments();
        if (args!=null) {
            setCancelable(args.getBoolean(ARGS_CANCANCEL_KET,false));
            canBack = args.getBoolean(ARGS_CANBACK_KET,false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        this.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface arg0, int keyCode, KeyEvent arg2) {
                // TODO Auto-generated method stub 返回键关闭dialog
                if (keyCode == KeyEvent.KEYCODE_BACK && canBack) {
                    dismiss();
                    return true;
                }
                return false;
            }
        });

        View view = inflater.inflate(R.layout.dialog_prompt, container, false);
        initViews(view);
        setEvent();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window;
        if (getDialog() != null) {
            window = getDialog().getWindow();
        } else {
            // This DialogFragment is used as a normal fragment, not a dialog
            window = getActivity().getWindow();
        }
        if (window != null) {
            window.setBackgroundDrawableResource(android.R.color.transparent);
        }
    }

    private void initViews(View view) {
        this.ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        this.tvBtn = (TextView) view.findViewById(R.id.tv_btn);
        this.tvRight = (TextView) view.findViewById(R.id.tv_right);
        this.tvLeft = (TextView) view.findViewById(R.id.tv_left);
        this.tvPromptContent = (TextView) view.findViewById(R.id.tv_prompt_content);
        this.llDoubleBtn = (LinearLayout) view.findViewById(R.id.ll_double_btn);

        Bundle args = getArguments();
        if (args!=null){
            int type = args.getInt(ARGS_DIALOG_TYPE_KET);
            ivIcon.setImageDrawable(getResources().getDrawable(args.getInt(ARGS_DRAWABLE_ID_KET,R.drawable.ic_unanswer)));
            tvPromptContent.setText(args.getString(ARGS_MESSAGE_TEXT_KET,"message"));
            if (type==TWO_BUTTON){
                tvBtn.setVisibility(View.GONE);
                llDoubleBtn.setVisibility(View.VISIBLE);
                tvLeft.setText(args.getString(ARGS_LEFT_TEXT_KET,""));
                tvRight.setText(args.getString(ARGS_RIGHT_TEXT_KET,""));
            }else{
                llDoubleBtn.setVisibility(View.GONE);
                tvBtn.setVisibility(View.VISIBLE);
                tvBtn.setText(args.getString(ARGS_CANCEL_TEXT_KET,""));
            }
        }
    }

    private void setEvent() {
        tvRight.setOnClickListener(this);
        tvLeft.setOnClickListener(this);
        tvBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_btn) {
            if (dismissListener != null) {
                dismissListener.onDismiss();
            }
        } else if(v.getId() == R.id.tv_right) {
            if (closeListener != null) {
                closeListener.onRightClick();
            }
        } else if(v.getId() == R.id.tv_left) {
            if (closeListener != null) {
                closeListener.onLeftClick();
            }
        }
        dismiss();
    }
}
