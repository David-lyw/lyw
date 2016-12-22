package com.example.david.lyw;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import com.example.david.bean.rankingBean;
import com.example.david.http.HttpApiService;
import com.example.david.http.HttpUtilsHolder;
import com.example.david.widget.Contants;
import com.lyw.BaseActivity;
import com.lyw.http.HttpResult;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppActivity {
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_click);

        //接口4.4 为例:
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Observable<HttpResult<List<rankingBean>>> observable = (Observable<HttpResult<List<rankingBean>>>) HttpUtilsHolder.getHttpUtils().executeService(HttpApiService.class,
                        "ranking",
                        new Class[]{String.class, String.class},
                        new Object[]{"1", "30"});

                observable.subscribe(new Subscriber<HttpResult<List<rankingBean>>>() {
                    @Override
                    public void onCompleted() {
                        Log.i(Contants.lyw, "加载完成");
                    }

                    @Override
                    public void onError(Throwable e) {
                       e.printStackTrace();
                    }

                    @Override
                    public void onNext(HttpResult<List<rankingBean>> listHttpResult) {
                        if (listHttpResult.code.equals("1000")) {
                            List<rankingBean> data = listHttpResult.data;
                            for (int i = 0; i < data.size(); i++) {
                                Log.i(Contants.lyw, "数据:  " + data.get(i).name);

                            }
                        }
                    }
                });
            }
        });
    }

}
