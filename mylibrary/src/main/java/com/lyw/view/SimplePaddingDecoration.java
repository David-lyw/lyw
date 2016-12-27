package com.lyw.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import business.esale.hivedrp.com.mylibrary.R;


/**
 * Created by David on 16/12/19.
 * RecycleView 的ItemDecoration
 */
public class SimplePaddingDecoration extends RecyclerView.ItemDecoration {
    private int dividerHeight;


    public SimplePaddingDecoration(Context context, int dividerHeight) {
        //dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.px_32);
        //dividerHeight = context.getResources().getDimensionPixelSize(R.dimen.SimplePaddingDecoration);
        this.dividerHeight = dividerHeight;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.bottom = dividerHeight;//类似加了一个bottom padding
    }
}
