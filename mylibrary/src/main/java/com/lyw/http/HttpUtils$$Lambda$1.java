package com.lyw.http;

import okhttp3.ResponseBody;
import rx.functions.Action1;

/**
 * Created by David on 16/12/21.
 * <p/>
 * 和Lambda相关,但还不明白什么原理。(程序中没用到)
 */
final class HttpUtils$$Lambda$1 implements Action1 {
    private final String arg$1;

    private HttpUtils$$Lambda$1(String var1) {
        this.arg$1 = var1;
    }

    private static Action1 get$Lambda(String var0) {
        return new HttpUtils$$Lambda$1(var0);
    }


    @Override
    public void call(Object o) {
        //HttpUtils.access$lambda$0(this.arg$1,(ResponseBody)o);
    }

    public static Action1 lambdaFactory$(String var0) {
        return new HttpUtils$$Lambda$1(var0);
    }
}
