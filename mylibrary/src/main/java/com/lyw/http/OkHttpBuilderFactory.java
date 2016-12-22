package com.lyw.http;

import com.lyw.BaseApplication;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;


/**
 * Created by David on 16/12/21.
 */
public enum OkHttpBuilderFactory {
    INSTANCE;

    private final OkHttpClient.Builder mBuilder;
    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;

    private OkHttpBuilderFactory() {

        this.mBuilder = (new OkHttpClient.Builder())
                .addInterceptor((new HttpLoggingInterceptor()).setLevel(HttpLoggingInterceptor.Level.BODY))//添加拦截器
                .cache(new Cache(BaseApplication.getApplication().getCacheDir(), 10485760L))
                .addInterceptor(new NetworkCachedInterceptor())//添加拦截器
                .addNetworkInterceptor(new NetworkCachedInterceptor())//添加拦截器
                .retryOnConnectionFailure(true)
                .readTimeout(25L, TimeUnit.SECONDS)
                .connectTimeout(25L, TimeUnit.SECONDS);

//        this.mBuilder = (new OkHttpClient.Builder()).cache(new Cache(BaseApplication.getApplication().getCacheDir(), 10485760L))
//                .addInterceptor(new NetworkCachedInterceptor())
//                .addNetworkInterceptor(new NetworkCachedInterceptor())
//                .retryOnConnectionFailure(true)
//                .readTimeout(25L, TimeUnit.SECONDS)
//                .connectTimeout(25L, TimeUnit.SECONDS);
    }

    public OkHttpClient build() {
        return this.mBuilder.build();
    }

    public OkHttpClient.Builder getOkHttpClientBuilder() {
        return this.mBuilder;
    }
}
