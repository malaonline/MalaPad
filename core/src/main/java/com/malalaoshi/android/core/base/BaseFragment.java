package com.malalaoshi.android.core.base;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by kang on 16/12/8.
 */

public class BaseFragment extends Fragment {
    private DialogFragment dialogFragment;
    protected void showDialog(DialogFragment dialog) {
        final String FLAG = "dialogFragment";
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
    }


    @Override
    public void onResume() {
        super.onResume();
        if (dialogFragment != null) {
            showDialog(dialogFragment);
        }
    }
}
