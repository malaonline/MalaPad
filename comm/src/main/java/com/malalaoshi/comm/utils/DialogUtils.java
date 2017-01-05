package com.malalaoshi.comm.utils;

import com.malalaoshi.comm.views.dialogs.PromptDialog;

/**
 * Created by kang on 17/1/5.
 */

public class DialogUtils {
    public static PromptDialog createDoubleButtonPromptDialog(int drawableId, String message, String leftText, String rightText, PromptDialog.OnCloseListener onCloseListener, boolean cancelable, boolean backable){
        PromptDialog promptDialog = PromptDialog.newInstance(drawableId, message, leftText, rightText, cancelable,backable);
        promptDialog.setCloseListener(onCloseListener);
        return promptDialog;
    }

    public static PromptDialog createPromptDialog(int drawableId, String message, String btnText,PromptDialog.OnDismissListener onDismissListener , boolean cancelable, boolean backable){
        PromptDialog promptDialog = PromptDialog.newInstance(drawableId, message, btnText,cancelable, backable);
        promptDialog.setDismissListener(onDismissListener);
        return promptDialog;
    }
}
