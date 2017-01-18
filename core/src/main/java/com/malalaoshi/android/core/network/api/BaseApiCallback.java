package com.malalaoshi.android.core.network.api;


import com.malalaoshi.android.core.AppContext;
import com.malalaoshi.android.core.R;

/**
 * Created by kang on 16/12/26.
 */

public abstract class BaseApiCallback<T> implements ApiCallback<T> {
    public static final int ERROR_CODE_BAD_NET       = 600;
    public static final int ERROR_CODE_IO_EXECPTION  = 601;
    public static final int ERROR_CODE_UNKNOWN_EXECPTION  = 602;
    public static final int ERROR_CODE_NULL_EXECPTION  = 603;
    public static final int ERROR_CODE_TOKEN_INVALID = -1;
    public static final int ERROR_CODE_TOKEN_LOGOUT  = -2;
    public static String getHost() {
        return AppContext.getContext().getString(R.string.api_host);
    }

    @Override
    public void onApiStarted() {

    }

    @Override
    public void onFailure(int code, String msg) {

    }

    @Override
    public void onFinish() {

    }
}
