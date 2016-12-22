package com.lyw.http;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by David on 16/12/21.
 * 拦截器
 */
public class NetworkCachedInterceptor implements Interceptor{
    private static final int MAX_AGE = 30;

    public NetworkCachedInterceptor() {
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        if(!HttpUtils.isNetworkConnected()) {
            request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
        }

        Response response = chain.proceed(request);
        if(HttpUtils.isNetworkConnected()) {
            byte maxStale = 30;
            response.newBuilder().header("Cache-Control", "public, max-age=" + maxStale).removeHeader("Pragma").build();
        } else {
            int maxStale1 = 2419200;
            response.newBuilder().header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale1).removeHeader("Pragma").build();
        }
        return response;
    }
}
