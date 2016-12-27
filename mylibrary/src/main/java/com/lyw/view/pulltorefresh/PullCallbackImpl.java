package com.lyw.view.pulltorefresh;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.lyw.adapter.HolderAdapter;
import com.lyw.utils.ReflectionUtil;

import java.util.List;

/**
 * Created by David on 16/12/27.
 */
public abstract class PullCallbackImpl implements IPullCallback{
    private PullToLoadView mPullToLoadView;
    private RecyclerView mRecyclerView;
    private boolean mIsLoading;
    private boolean mIsLoadedAll;
    private HolderAdapter mAdapter;
    protected int mCurrentPage;

    public PullCallbackImpl(PullToLoadView pullToLoadView) {
        this(pullToLoadView, (RecyclerView.LayoutManager)null);
    }

    public PullCallbackImpl(PullToLoadView pullToLoadView, RecyclerView.LayoutManager layoutManager) {
        this.mPullToLoadView = pullToLoadView;
        this.mRecyclerView = this.mPullToLoadView.getRecyclerView();
        if(layoutManager == null) {
            LinearLayoutManager manager = new LinearLayoutManager(this.mPullToLoadView.getContext(), 1, false);
            this.mRecyclerView.setLayoutManager(manager);
        } else {
            this.mRecyclerView.setLayoutManager(layoutManager);
        }

        this.mPullToLoadView.isLoadMoreEnabled(true);
    }

    public void onLoadMore() {
        this.mIsLoading = true;
        this.requestData(this.mCurrentPage + 1, true);
    }

    public void onRefresh() {
        this.mIsLoading = true;
        this.requestData(1, false);
    }

    protected abstract void requestData(int var1, boolean var2);

    public HolderAdapter handleData(int pager, List retList, Class clazz, boolean follow) {
        return this.handleData(pager, retList, clazz, follow, (View)null);
    }

    public HolderAdapter handleData(int pager, List retList, Class clazz, boolean follow, View header) {
        if(retList != null && !retList.isEmpty()) {
            if(this.mAdapter == null) {
                this.mAdapter = (HolderAdapter) ReflectionUtil.generateObject(clazz, Context.class, List.class, new Object[]{this.mPullToLoadView.getContext(), retList});
                if(header != null) {
                    this.mAdapter.setHeader(header);
                }

                this.mRecyclerView.setAdapter(this.mAdapter);
            } else if(!follow) {
                this.mAdapter.setListData(retList);
            } else {
                this.mAdapter.appendListData(retList);
            }

            this.mCurrentPage = pager;
        }

        this.loadOK();
        return this.mAdapter;
    }

    public boolean isLoading() {
        return this.mIsLoading;
    }

    public boolean hasLoadedAllItems() {
        return this.mIsLoadedAll;
    }

    public void loadOK() {
        this.mIsLoading = false;
        this.mPullToLoadView.setComplete();
    }
}
