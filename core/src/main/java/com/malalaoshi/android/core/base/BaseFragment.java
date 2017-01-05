package com.malalaoshi.android.core.base;


import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;

import java.lang.ref.WeakReference;

/**
 * Created by kang on 16/12/8.
 */

public class BaseFragment extends Fragment {
    private WeakReference<DialogFragment> dialogFragment;
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
        dialogFragment = new WeakReference<DialogFragment>(dialog);
    }

    protected void deleteDialog(){
        DialogFragment dialog = dialogFragment.get();
        if (dialog!=null){
            dialog.dismiss();
            dialogFragment.clear();
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        DialogFragment dialog = dialogFragment.get();
        if (dialog != null) {
            showDialog(dialog);
        }
    }
}
