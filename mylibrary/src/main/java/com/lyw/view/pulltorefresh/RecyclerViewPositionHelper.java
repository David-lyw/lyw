package com.lyw.view.pulltorefresh;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.view.View;

/**
 * Created by David on 16/11/8.
 */
public class RecyclerViewPositionHelper {
    final RecyclerView recyclerView;
    final LayoutManager layoutManager;


    RecyclerViewPositionHelper(RecyclerView recyclerView1) {
        this.recyclerView = recyclerView1;
        this.layoutManager = recyclerView1.getLayoutManager();
    }

    public static RecyclerViewPositionHelper createHelper(RecyclerView recyclerView2) {
        if (recyclerView2 == null) {
            throw new NullPointerException("Recyclerview is null");
        } else {
            return new RecyclerViewPositionHelper(recyclerView2);
        }
    }

    public int getItemCount() {//返回item的数目
        return this.layoutManager == null ? 0 : this.layoutManager.getItemCount();
    }

    public int findFirstVisibleItemPosition() {
        View child = this.findOneVisibleChild(0, this.layoutManager.getChildCount(), false, true);
        return child == null ? -1 : this.recyclerView.getChildAdapterPosition(child);
    }

    public int findFirstCompletelyVisibleItemPosition() {
        View child = this.findOneVisibleChild(0, this.layoutManager.getChildCount(), true, false);
        return child == null ? -1 : this.recyclerView.getChildAdapterPosition(child);
    }

    public int findLastVisibleItemPosition() {
        View child = this.findOneVisibleChild(this.layoutManager.getChildCount() - 1, -1, false, true);
        return child == null ? -1 : this.recyclerView.getChildAdapterPosition(child);
    }

    public int findLastCompletelyVisibleItemPosition() {
        View child = this.findOneVisibleChild(this.layoutManager.getChildCount() - 1, -1, true, false);
        return child == null ? -1 : this.recyclerView.getChildAdapterPosition(child);
    }

    public View findOneVisibleChild(int fromIndex, int toIndex, boolean completelyVisible, boolean acceptPartiallyVisible) {
        OrientationHelper helper;
        if (this.layoutManager.canScrollVertically()) {
            helper = OrientationHelper.createVerticalHelper(this.layoutManager);
        } else {
            helper = OrientationHelper.createHorizontalHelper(this.layoutManager);
        }

        int start = helper.getStartAfterPadding();
        int end = helper.getEndAfterPadding();
        int next = toIndex > fromIndex ? 1 : -1;
        View partiallyVisible = null;

        for (int i = fromIndex; i != toIndex; i += next) {

            View child = this.layoutManager.getChildAt(i);
            int childStart = helper.getDecoratedStart(child);
            int childEnd = helper.getDecoratedEnd(child);
            if (childStart < end && childEnd > start) {
                if (!completelyVisible) {
                    return child;
                }
                if (childStart >= start && childEnd <= end) {
                    return child;
                }

                if (acceptPartiallyVisible && partiallyVisible == null) {
                    partiallyVisible = child;
                }
            }

        }
        return partiallyVisible;
    }
}
