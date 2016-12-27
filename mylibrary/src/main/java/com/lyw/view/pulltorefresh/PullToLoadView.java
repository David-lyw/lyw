package com.lyw.view.pulltorefresh;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import business.esale.hivedrp.com.mylibrary.R;

/**
 * Created by David on 16/10/27.
 * 自定义控件。
 */
public class PullToLoadView extends FrameLayout {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;

    private RotateLoading progressBar;
    private IPullCallback mPullCallback;
    private RecyclerViewPositionHelper mRecyclerViewHelper;
    protected ScrollDirection mScrollDirection;
    protected int mPrevFirstVisibleItem;
    private int mLoadMoreOffset;
    private boolean mIsLoadMoreEnabled;

    private ViewGroup layout;

    public PullToLoadView(Context context, AttributeSet attrs) {
        this(context,attrs,0);
    }

    public PullToLoadView(Context context) {
       this(context,(AttributeSet)null);
    }

    public PullToLoadView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mPrevFirstVisibleItem = 0;
        this.mLoadMoreOffset = 5;
        this.mIsLoadMoreEnabled = false;
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.layout=(ViewGroup) mInflater.inflate(R.layout.pull_to_refresh_view, this, true);
        this.swipeRefreshLayout = (SwipeRefreshLayout) this.layout.findViewById(R.id.swipeRefreshLayout);
        this.recyclerView = (RecyclerView) this.layout.findViewById(R.id.recyclerView);
        this.progressBar = (RotateLoading) this.layout.findViewById(R.id.progressBar);
        this.init();
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.mRecyclerViewHelper = RecyclerViewPositionHelper.createHelper(this.recyclerView);
    }

    private void init() {
        this.swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initLoad();
            }
        });
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                PullToLoadView.this.mScrollDirection = null;

            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int totalItemCount;
                if (PullToLoadView.this.mScrollDirection == null) {
                    PullToLoadView.this.mScrollDirection = ScrollDirection.SAME;
                    PullToLoadView.this.mPrevFirstVisibleItem = PullToLoadView.this.mRecyclerViewHelper.findFirstVisibleItemPosition();
                } else {
                    totalItemCount = PullToLoadView.this.mRecyclerViewHelper.findFirstVisibleItemPosition();
                    if (totalItemCount > PullToLoadView.this.mPrevFirstVisibleItem) {
                        PullToLoadView.this.mScrollDirection = ScrollDirection.UP;
                    } else if (totalItemCount < PullToLoadView.this.mPrevFirstVisibleItem) {
                        PullToLoadView.this.mScrollDirection = ScrollDirection.DOWN;
                    } else {
                        PullToLoadView.this.mScrollDirection = ScrollDirection.SAME;
                    }
                    PullToLoadView.this.mPrevFirstVisibleItem = totalItemCount;
                }

                if (PullToLoadView.this.mIsLoadMoreEnabled && PullToLoadView.this.mScrollDirection == ScrollDirection.UP && !PullToLoadView.this.mPullCallback.isLoading() && !PullToLoadView.this.mPullCallback.hasLoadedAllItems()) {
                    totalItemCount = PullToLoadView.this.mRecyclerViewHelper.getItemCount();
                    int firstVisibleItem = PullToLoadView.this.mRecyclerViewHelper.findFirstVisibleItemPosition();
                    int visibleItemCount = Math.abs(PullToLoadView.this.mRecyclerViewHelper.findLastVisibleItemPosition() - firstVisibleItem);
                    int lastAdapterPosition = totalItemCount - 1;
                    int lastVisiblePosition = firstVisibleItem + visibleItemCount - 1;
                    if (lastVisiblePosition >= lastAdapterPosition - PullToLoadView.this.mLoadMoreOffset && null != PullToLoadView.this.mPullCallback) {
                        PullToLoadView.this.progressBar.start();
                        PullToLoadView.this.mPullCallback.onLoadMore();
                    }
                }
            }
        });


    }

    public void setComplete() {
        this.progressBar.stop();
        this.swipeRefreshLayout.setRefreshing(false);
    }

    public void initLoad() {
        if (null != this.mPullCallback) {
            this.swipeRefreshLayout.setRefreshing(true);
            this.mPullCallback.onRefresh();
        }

    }

    public void setLoadingColor(int colorResId) {
        this.swipeRefreshLayout.setColorSchemeResources(new int[]{colorResId});
        this.progressBar.setColor(colorResId);
    }

    public RecyclerView getRecyclerView() {
        return this.recyclerView;
    }

    public void setPullCallback(IPullCallback mPullCallback) {
        this.mPullCallback = mPullCallback;
    }

    public void setLoadMoreOffset(int offset) {
        this.mLoadMoreOffset = offset;
    }

    public void isLoadMoreEnabled(boolean enabled) {
        this.mIsLoadMoreEnabled = enabled;
    }
}
