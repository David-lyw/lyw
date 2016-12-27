package com.lyw.view.pulltorefresh;

/**
 * Created by David on 16/11/8.
 */
public interface IPullCallback {
    void onLoadMore();

    void onRefresh();

    boolean isLoading();

    boolean hasLoadedAllItems();

}
