package com.lyw.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import business.esale.hivedrp.com.mylibrary.R;

/**
 * Created by David on 16/12/27.
 */
public class CommonDialog {

    Context context;
    public int layoutId;
    ViewGroup layout;

    Dialog dialog;

    public CommonDialog(Context context, int layoutId) {
        this.layoutId = layoutId;
        this.context = context;
        initDialog(context);
    }

    public void initDialog(Context context) {
        dialog = new Dialog(context, R.style.Dialog);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        layout = (ViewGroup) inflater.inflate(layoutId, null);
        dialog.setContentView(layout);

        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        Window window = dialog.getWindow();
        window.setAttributes(params);
        window.getDecorView().setPadding(0, 0, 0, 0);
    }

    public void showDialog() {
        dialog.show();
    }

    public void dismissDialog() {
        dialog.dismiss();
    }


    public ViewGroup getConvertView() {
        return layout;
    }

    public <T extends View> T getView(int ViewId) {
        View view = getConvertView().findViewById(ViewId);
        return (T) view;
    }
}
