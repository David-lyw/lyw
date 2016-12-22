package com.lyw;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import rx.Observable;

/**
 * Created by David on 16/12/21.
 */
public class BaseActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((BaseApplication) this.getApplication()).addActivity(this);
    }

    protected void onDestroy() {
        ((BaseApplication) this.getApplication()).removeActivity(this);
        super.onDestroy();
    }





}
