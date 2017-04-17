package com.malalaoshi.android.core.base;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import com.malalaoshi.android.core.event.BusEvent;
import com.malalaoshi.android.core.event.BusEventDef;
import com.malalaoshi.android.core.event.EventDispatcher;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by kang on 16/12/8.
 */

public abstract class BaseFragment extends Fragment {
    private static String TAG = "BaseFragment";

    private DialogFragment dialogFragment;
    protected void showDialog(DialogFragment dialog) {
        final String FLAG = dialog.getClass().getName();
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag(FLAG);
        if (prev != null) {
            ft.remove(prev);
        }
        try {
            dialog.show(ft, FLAG);
        } catch (Exception e) {
            e.printStackTrace();
        }
        dialogFragment = null;
    }
    protected void addDialog(DialogFragment dialog) {
        dialogFragment = dialog;
    }

    protected void deleteDialog(){
        if (dialogFragment!=null){
            dialogFragment.dismiss();
            dialogFragment = null;
        }
        if (getPresent()!=null){

        }
    }

    protected abstract BasePresenter getPresent();


    @Override
    public void onResume() {
        super.onResume();
        if (dialogFragment != null) {
            showDialog(dialogFragment);
        }
        if (getPresent()!=null){
            getPresent().subscribe();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (getPresent()!=null){
            getPresent().unsubscribe();
        }
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
    public void onAuthEvent(BusEvent busEvent) {
//        Log.e(TAG,busEvent.toString());
        if (busEvent.getEventType()== BusEventDef.BUS_EVENT_AUTO_LOGOUT_SUCCESS){
            //自动退出
            onAutoLogout();
        }else if (busEvent.getEventType()== BusEventDef.BUS_EVENT_TOKEN_INVALID){
            //账号被踢下线//自动退出
            onTokenInvalid();
        } else if (busEvent.getEventType()== BusEventDef.BUS_EVENT_USER_LOGOUT_SUCCESS){
            //用户主动退出
            onUserLogout();
        }
    }

    private void onUserLogout() {
    }

    protected void onAutoLogout() {

    }

    protected void onTokenInvalid() {
    }

}
