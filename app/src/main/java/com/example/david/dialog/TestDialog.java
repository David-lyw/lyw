package com.example.david.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.example.david.lyw.R;
import com.lyw.dialog.CommonDialog;

/**
 * Created by David on 16/12/27.
 */
public class TestDialog extends CommonDialog {

    public TestDialog(Context context) {
        super(context, R.layout.dialog_share);
    }


    @Override
    public void showDialog() {
        Button btn = (Button) getView(R.id.btn_cancel);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewClick != null) {
                    viewClick.onClick(1);
                    dismissDialog();//调用父类的dimiss方法。
                }
            }
        });
        super.showDialog();//调用父类的显示方法。
    }


    public interface ViewClick {
        void onClick(int tag);
    }

    public ViewClick viewClick;

    public void setViewClick(ViewClick viewClick) {
        this.viewClick = viewClick;
    }
}
