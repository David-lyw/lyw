package com.example.david.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.example.david.lyw.R;
import com.lyw.adapter.CommonAdapter;

import java.util.List;

/**
 * Created by David on 16/12/27.
 * 使用范围:listview,gridview,及相关的自定义的控件。
 */
public class commontestAdapter extends CommonAdapter<String> {

    public commontestAdapter(Context context, List<String> list) {
        super(context, R.layout.adapter_commontest, list);
    }


    @Override
    protected void setViews(final Viewholder holder, final String s) {
//        ImageView iv = holder.getView(R.id.iv_content);
//        ImageView iv_check = holder.getView(R.id.iv_check);
    }








}
