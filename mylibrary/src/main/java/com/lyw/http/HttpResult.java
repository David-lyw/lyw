package com.lyw.http;

/**
 * Created by David on 16/12/21.
 * 后台返回的数据,可以根据实际情况调整。
 */
public class HttpResult<T> {
    public String code;
    public String message;
    public String pageNo;
    public String pageSize;
    public String totalPages;
    public T data;

    public HttpResult() {
    }
}