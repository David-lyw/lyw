package com.example.david.lyw;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.david.http.HttpUtilsHolder;
import com.lyw.BaseActivity;

import rx.Observable;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by David on 16/12/21.
 */
public class AppActivity extends BaseActivity {

    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        handleMaterialStatusBar();

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    public void showProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this, "", "正在加载...");
        }
        if (!mProgressDialog.isShowing()) {
            mProgressDialog.show();
        }
    }

    public void dismissProgress() {
        if (mProgressDialog == null) {
            mProgressDialog = ProgressDialog.show(this, "", "正在加载...");
        }
        if (mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    public Object requestHttp(Class clazz, String methodName, Class[] paramClasses, Object[] params) {

//        return ((Observable) HttpUtilsHolder.getHttpUtils().executeService(clazz, methodName, paramClasses, params))
//                .doOnSubscribe(this::showProgress).doOnCompleted(this::dismissProgress).doOnError(e -> dismissProgress());
//
        return ((Observable) HttpUtilsHolder.getHttpUtils().executeService(clazz, methodName, paramClasses, params))
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        showProgress();
                    }
                })
                .doOnCompleted(new Action0() {
                    @Override
                    public void call() {
                        dismissProgress();
                    }
                })
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        dismissProgress();
                    }
                });

    }


    /**
     * 沉浸式状态栏只支持安卓4.4及以上的版本
     * API等级21：Android 5.0 Lollipop
     */
    public void handleMaterialStatusBar() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP)
            return;

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(0xffE46c62);
    }

}
