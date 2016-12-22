package com.example.david.http;


import com.example.david.widget.Contants;
import com.lyw.http.HttpUtils;

/**
 * Created by henry on 16/8/12.
 */
public class HttpUtilsHolder {
    //private static String BASE_URL = "http://emall.eteclab.com/api/v1/";//正式打包基地址。
    //private static String BASE_URL = "http://192.168.1.10:8001/api/v1/";//内部网测试基地址。
    private static String BASE_URL = Contants.BASE+"api/v1/";
    private static final HttpUtilsHolder INSTANCE = new HttpUtilsHolder();
    private HttpUtils mHttpUtils;

    private HttpUtilsHolder() {
        mHttpUtils = new HttpUtils(BASE_URL);
    }

    public static HttpUtils getHttpUtils() {
        return INSTANCE.mHttpUtils;
    }
}