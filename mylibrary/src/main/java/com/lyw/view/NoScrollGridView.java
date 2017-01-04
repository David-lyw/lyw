package com.lyw.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.lyw.adapter.AbstractAdapter;
import com.lyw.adapter.HolderAdapter;

/**
 * Created by David on 17/1/4.
 */
public class NoScrollGridView<T> extends GridView {
    public NoScrollGridView(Context context) {
        super(context);
    }

    public NoScrollGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override  //536870911
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

    public void setAdapter(final HolderAdapter<T> adapter) {
        AbstractAdapter listAdapter = new AbstractAdapter(this.getContext(), adapter.getData()) {
            public View getView(int position, View convertView, ViewGroup parent) {
                RecyclerView.ViewHolder holder = adapter.onCreateViewHolder(parent, 0);
                adapter.onBindViewHolder(holder, position);
                return holder.itemView;
            }
        };
        this.setAdapter(listAdapter);
    }
}
