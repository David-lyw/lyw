package com.lyw.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.lyw.adapter.HolderAdapter;

import business.esale.hivedrp.com.mylibrary.R;

/**
 * Created by David on 17/1/4.
 */
public class NoScrollListView<T> extends LinearLayout {
    private HolderAdapter<T> mAdapter;
    private NoScrollListView.ItemDecoration mItemDecoration;

    public NoScrollListView(Context context) {
        this(context, (AttributeSet) null);
    }

    public NoScrollListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NoScrollListView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        this.setOrientation(VERTICAL);
    }

    public HolderAdapter<T> getAdapter() {
        return this.mAdapter;
    }

    public void setItemDecoration(NoScrollListView.ItemDecoration itemDecoration) {
        this.mItemDecoration = itemDecoration;
    }

    public void setAdapter(HolderAdapter<T> adapter) {
        this.removeAllViews();
        this.mAdapter = adapter;
        int size = adapter.getItemCount();

        for (int i = 0; i < size; ++i) {
            RecyclerView.ViewHolder holder = adapter.createViewHolder(this, adapter.getItemViewType(i));
            adapter.onBindViewHolder(holder, i);
            this.addView(holder.itemView);
            if (this.mItemDecoration == null) {
                View rect = new View(this.getContext());
                rect.setLayoutParams(new LayoutParams(-1, 1));
                rect.setBackgroundColor(getResources().getColor(R.color.color_fff));
                rect.setPadding(10, 10, 10, 10);
                this.addView(rect);
            } else if (this.mItemDecoration.getView() != null) {
                this.addView(this.mItemDecoration.getView());
            } else if (this.mItemDecoration.getRect() != null) {
                Rect var7 = this.mItemDecoration.getRect();
                LayoutParams lp = (LayoutParams) holder.itemView.getLayoutParams();
                lp.leftMargin += var7.left;
                lp.rightMargin += var7.right;
                lp.topMargin += var7.top;
                lp.bottomMargin += var7.bottom;
                holder.itemView.setLayoutParams(lp);
            }
        }

    }


    public abstract static class ItemDecoration {
        public ItemDecoration() {
        }

        public abstract View getView();

        public abstract Rect getRect();
    }
}
