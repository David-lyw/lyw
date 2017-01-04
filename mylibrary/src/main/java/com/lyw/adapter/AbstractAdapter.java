package com.lyw.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

import java.util.List;

/**
 * Created by David on 17/1/4.
 */
public abstract class AbstractAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mLayoutInflater;

    public AbstractAdapter(Context context, List<T> listData) {
        this.mContext = context;
        this.mData = listData;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return this.mData != null ? this.mData.size() : 0;
    }

    @Override
    public T getItem(int position) {
        return this.mData != null ? this.mData.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return (long) position;
    }

    public List<T> getListData() {
        return this.mData;
    }

    public void setListData(List<T> data) {
        this.mData = data;
    }

    public void appendListData(List<T> data) {
        this.mData.addAll(data);
    }
}
