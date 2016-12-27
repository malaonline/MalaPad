package com.malalaoshi.android.core.network.api;

/**
 * Created by kang on 16/12/9.
 */

public interface ApiCallback<T> {
  /*  public static String getHost() {
        return AppContext.getContext().getString(R.string.app_name);
    }*/

    public abstract void onApiStarted();

    public abstract void onSuccess(T t);

    public abstract void onFailure(int code, String msg);

    public abstract void onThrowable(Throwable t);

    public abstract void onFinish();
}
