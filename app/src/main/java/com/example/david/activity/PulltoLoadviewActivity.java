package com.example.david.activity;

import android.os.Bundle;
import android.util.Log;

import com.example.david.Dto.CollectionDto;
import com.example.david.adapter.FollowAdapter;
import com.example.david.http.HttpApiService;
import com.example.david.http.HttpUtilsHolder;
import com.example.david.lyw.AppActivity;
import com.example.david.lyw.R;
import com.example.david.widget.Contants;
import com.lyw.http.HttpResult;
import com.lyw.view.pulltorefresh.IPullCallback;
import com.lyw.view.pulltorefresh.PullCallbackImpl;
import com.lyw.view.pulltorefresh.PullToLoadView;

import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * Created by David on 16/12/27.
 */
public class PulltoLoadviewActivity extends AppActivity {

    PullToLoadView pageone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulltoloadview);
        pageone = (PullToLoadView) findViewById(R.id.datalist);
        pageone.setPullCallback(new PullCallbackImpl(pageone) {
            @Override
            protected void requestData(final int var1, final boolean var2) {

                Observable<HttpResult<List<CollectionDto>>> observable = (Observable<HttpResult<List<CollectionDto>>>) HttpUtilsHolder.getHttpUtils().executeService(HttpApiService.class,
                        "getCollectionList",
                        new Class[]{String.class, String.class, String.class},
                        new Object[]{240 + "", var1 + "", "20"});

                observable.subscribe(new Subscriber<HttpResult<List<CollectionDto>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        e.printStackTrace();

                    }

                    @Override
                    public void onNext(HttpResult<List<CollectionDto>> listHttpResult) {

                        handleData(var1,listHttpResult.data, FollowAdapter.class,var2);

                    }
                });
            }
        });

        pageone.initLoad();

    }
}
