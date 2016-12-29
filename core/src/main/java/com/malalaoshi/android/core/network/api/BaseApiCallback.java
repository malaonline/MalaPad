package com.malalaoshi.android.core.network.api;


import com.malalaoshi.android.core.AppContext;
import com.malalaoshi.android.core.R;

/**
 * Created by kang on 16/12/26.
 */

public abstract class BaseApiCallback<T> implements ApiCallback<T> {

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
    public void onThrowable(Throwable t) {

    }

    @Override
    public void onFinish() {

    }
}
