package com.example.david.lyw;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.david.activity.HolderActivity;
import com.example.david.activity.ImagetestActivity;
import com.example.david.activity.PulltoLoadviewActivity;
import com.example.david.bean.rankingBean;
import com.example.david.dialog.TestDialog;
import com.example.david.http.HttpApiService;
import com.example.david.http.HttpUtilsHolder;
import com.example.david.widget.Contants;
import com.lyw.BaseActivity;
import com.lyw.http.HttpResult;
import com.lyw.view.BannerView;
import com.lyw.view.CountDownTimerUtils;
import com.lyw.view.GifView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

public class MainActivity extends AppActivity {
    public Button btn;
    public Button btn2;
    public BannerView bannerView;
    public TextView tv_getcode;
    public TextView tv_dialog;
    public TextView tv_pulltoloadview;
    public TextView tv_imagetest;

    private int[] gifResIds = {
            R.raw.bird,
            R.raw.bird,
            R.raw.bird,
            R.raw.bird
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button) findViewById(R.id.btn_click);
        btn2 = (Button) findViewById(R.id.btn_click2);
        bannerView = (BannerView) findViewById(R.id.bannerView);
        tv_getcode = (TextView) findViewById(R.id.tv_getcode);
        tv_dialog = (TextView) findViewById(R.id.tv_dialog);
        tv_pulltoloadview = (TextView) findViewById(R.id.tv_pulltoloadview);
        tv_imagetest = (TextView) findViewById(R.id.tv_imagetest);

        tv_imagetest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ImagetestActivity.class);
                startActivity(intent);
            }
        });

        final CountDownTimerUtils timerUtils = new CountDownTimerUtils(tv_getcode, 60000, 1000);
        tv_getcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerUtils.start();
            }
        });

        tv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final TestDialog dialog = new TestDialog(MainActivity.this);
                dialog.setViewClick(new TestDialog.ViewClick() {
                    @Override
                    public void onClick(int tag) {
                        switch (tag) {
                            case 1:
                                //dialog.dismissDialog();
                                Toast.makeText(MainActivity.this, "ddddd", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                });
                dialog.showDialog();
            }
        });


        //setCarousel();
        setGifCarousel();

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

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HolderActivity.class);
                startActivity(intent);
            }
        });

        tv_pulltoloadview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PulltoLoadviewActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setCarousel() {
        List<View> list = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            ImageView iv = new ImageView(this);
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            Picasso.with(this).load(R.mipmap.test_carousel2).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(iv);
            list.add(iv);
        }
        bannerView.setViewPagerViews(list);
        //bannerView.start();
    }

    public void setGifCarousel() {
        List<View> list = new ArrayList<>();
        for (int i = 0; i < gifResIds.length; i++) {
            GifView iv = new GifView(this);
            iv.setMovieResource(gifResIds[i]);
            //iv.setScaleType(ImageView.ScaleType.FIT_XY);
            // Picasso.with(this).load(R.mipmap.test_carousel2).placeholder(R.mipmap.ic_launcher).error(R.mipmap.ic_launcher).into(iv);
            list.add(iv);
        }
        bannerView.setViewPagerViews(list);
        //bannerView.start();
    }


    //打开 辅助功能 界面。
    public void openAccessibility(View view) {
        Intent intent = new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS);
        startActivity(intent);
    }

}
