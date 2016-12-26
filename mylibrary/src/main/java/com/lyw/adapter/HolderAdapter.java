package com.lyw.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by David on 16/12/26.
 * RecyclerView要求必须使用ViewHolder模式.
 */
public abstract class HolderAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_HEADER = 1;
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mLayoutInflater;
    protected int layoutId;
    protected HolderAdapter.OnItemClickListener<T> mItemClickListener;
    private View mHeader;

    public HolderAdapter(Context context, int layoutId, List<T> data) {
        this.mContext = context;
        this.layoutId = layoutId;//布局id.
        this.mData = data;
        this.mLayoutInflater = LayoutInflater.from(context);
    }

    /**
     * @param data :这个数据列表必需是个新的list(不同于mData)引用。
     */
    public final void setListData(List<T> data) {
        this.mData.clear();
        this.mData.addAll(data);
        this.notifyDataSetChanged();
    }

    public final void appendListData(List<T> data) {
        this.mData.addAll(data);
        this.notifyDataSetChanged();
    }

    public final void removeItem(int index) {
        if (index < this.mData.size()) {
            this.mData.remove(index);
            this.notifyItemRemoved(index);
        }

    }

    /**
     * 设置头view
     *
     * @param header
     */
    public void setHeader(View header) {
        this.mHeader = header;
    }


    public List<T> getData() {
        return this.mData;
    }


    public final int getItemCount() {
        return this.mHeader != null ? (this.mData.size() + 1) : this.mData.size();
    }

    /**
     * 1:表示是headView 0:表示是其他item.
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        return this.mHeader != null && position == 0 ? 1 : 0;
    }


    @Override
    public final RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return (RecyclerView.ViewHolder) (viewType == 1 ? new HolderAdapter.BannerViewHolder(this.mHeader)
//                : this.generateViewHolder(InjectManager.injectView2Adapter(this, this.mContext, parent)));

        return (RecyclerView.ViewHolder) (viewType == 1 ? new HolderAdapter.BannerViewHolder(this.mHeader)
                : this.generateViewHolder(layId2View(null)));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        if (holder instanceof HolderAdapter.BaseViewHolder) {
            if (this.mHeader == null) {
                this.bindView(holder, position);
            } else if (position != 0) {
                this.bindView(holder, position - 1);
            }

            if (this.mItemClickListener != null) {
                if (this.mHeader != null) {
                    if (position != 0) {
                        holder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mItemClickListener.onItemClick(v, mData.get(position), position);
                            }
                        });
                    }
                } else {
                    holder.itemView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mItemClickListener.onItemClick(v, mData.get(position), position);
                        }
                    });
                }
            }
        }
    }

    protected abstract RecyclerView.ViewHolder generateViewHolder(View view);

    protected abstract void bindView(RecyclerView.ViewHolder view, int position);

    public final void setOnItemClickListener(HolderAdapter.OnItemClickListener<T> mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    //添加headview时用到。
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = (GridLayoutManager) manager;
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                public int getSpanSize(int position) {
                    return HolderAdapter.this.getItemViewType(position) == 1 ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }

    /**
     * 接口用于回调
     *
     * @param <T>
     */
    public interface OnItemClickListener<T> {
        void onItemClick(View view, T var2, int position);
    }


    class BannerViewHolder extends RecyclerView.ViewHolder {
        public BannerViewHolder(View view) {
            super(view);
        }
    }


    public class BaseViewHolder extends RecyclerView.ViewHolder {
        public View mConvertView;//布局。

        public BaseViewHolder(View view) {
            super(view);
            this.mConvertView = view;
        }
    }

    /**
     * 根据布局id,返回view
     */
    public View layId2View(ViewGroup parent) {
        View v = mLayoutInflater.inflate(layoutId, parent);
        return v;
    }

}
