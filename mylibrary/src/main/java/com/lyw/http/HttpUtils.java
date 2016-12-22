package com.lyw.http;


import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lyw.BaseApplication;
import com.lyw.utils.ReflectionUtil;

import retrofit2.Retrofit;
import okhttp3.ResponseBody;
import okhttp3.OkHttpClient.Builder;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by David on 16/12/21.
 */
public class HttpUtils {
    private Retrofit mRetrofit;

    public HttpUtils(String baseUrl) {
        this(baseUrl, (Builder)null);
    }

    public HttpUtils(String baseUrl, Builder builder) {
        if(builder == null) {
            this.mRetrofit = (new retrofit2.Retrofit.Builder()).client(OkHttpBuilderFactory.INSTANCE.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl).build();
        } else {
            this.mRetrofit = (new retrofit2.Retrofit.Builder()).client(builder.build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .baseUrl(baseUrl).build();
        }

    }

    public Object executeService(Class clazz, String methodName, Class[] paramClasses, Object[] params) {
        return ((Observable)this.executeServiceInIO(clazz, methodName, paramClasses, params)).observeOn(AndroidSchedulers.mainThread());
    }

    public Object executeServiceInIO(Class clazz, String methodName, Class[] paramClasses, Object[] params) {
        return ((Observable) ReflectionUtil.invokeMethod(this.mRetrofit.create(clazz), methodName, paramClasses, params)).subscribeOn(Schedulers.io());
    }





    public static boolean isNetworkConnected() {
        ConnectivityManager mConnectivityManager = (ConnectivityManager) BaseApplication.getApplication().getSystemService("connectivity");
        NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
        return mNetworkInfo != null && mNetworkInfo.isAvailable();
    }

}
