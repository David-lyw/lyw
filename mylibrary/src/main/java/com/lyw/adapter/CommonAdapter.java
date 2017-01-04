package com.lyw.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.StringRes;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David on 16/12/27.
 * 使用范围:listview,gridview.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mData;
    protected LayoutInflater mLayoutInflater;
    protected int layoutId;

    public CommonAdapter(Context context, int layoutid) {
        this(context, layoutid, new ArrayList<T>());
    }

    public CommonAdapter(Context context, int layoutId, List<T> list) {
        this.mContext = context;
        this.mData = list;
        this.layoutId = layoutId;
    }

    @Override
    public int getCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viewholder holder = Viewholder.getViewHolder(mContext, position,
                getLayoutId(), convertView, parent);
        setViews(holder, getItem(position));
        return holder.getConvertView();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public final T getItem(int position) {
        if (mData != null && mData.size() > position) {
            return mData.get(position);
        }
        return null;
    }

    public int getLayoutId() {
        return layoutId;
    }

    public void removeItem(int position) {
        mData.remove(position);
        notifyDataSetChanged(mData);
    }

    public void removeItem(T t) {
        mData.remove(t);
        notifyDataSetChanged(mData);
    }

    public void appendList(List<T> t) {
        mData.addAll(t);
        notifyDataSetChanged(mData);
    }


    public void notifyDataSetChanged(List<T> list) {
        this.mData = list;
        notifyDataSetChanged();
    }

    protected abstract void setViews(Viewholder holder, T t);


    public static class Viewholder {
        private View mConvertView;
        private int mPosition;
        private Context mContext;
        private SparseArray<View> mViews;

        protected Viewholder(Context context, int position, int layoutId, ViewGroup parent) {
            mViews = new SparseArray<View>();
            this.mPosition = position;
            this.mContext = context;
            mConvertView = LayoutInflater.from(mContext).inflate(layoutId, parent, false);
            mConvertView.setTag(this);
        }

        protected static Viewholder getViewHolder(Context context, int position, int layoutId, View convertView, ViewGroup parent) {
            if (convertView == null) {
                return new Viewholder(context, position, layoutId, parent);
            } else {
                Viewholder holder = (Viewholder) convertView.getTag();
                holder.mPosition = position;
                return holder;
            }
        }

        public View getConvertView() {
            return mConvertView;
        }

        public <T extends View> T getView(int ViewId) {
            View view = mViews.get(ViewId);
            if (view == null) {
                view = getConvertView().findViewById(ViewId);
                mViews.put(ViewId, view);
            }
            return (T) view;
        }

        /**
         * 此方法可以去掉(鸡肋)
         *
         * @param ViewId
         * @param data
         * @return
         */
        public Viewholder setText(int ViewId, String data) {
            View tv = getView(ViewId);
            if (tv instanceof TextView) {
                ((TextView) tv).setText(data);
            } else if (tv instanceof Button) {
                ((Button) tv).setText(data);
            }
            return this;
        }

        /**
         * 此方法可以去掉(鸡肋)
         *
         * @param ViewId
         * @param resid
         * @return
         */
        public Viewholder setText(int ViewId, @StringRes int resid) {
            try {
                return setText(ViewId, mContext.getResources().getString(resid));
            } catch (Resources.NotFoundException e) {
                return setText(ViewId, resid + "");
            }
        }


        public Viewholder setOnClickListener(View.OnClickListener l, int... ViewId) {
            for (int id : ViewId) {
                getView(id).setOnClickListener(l);
            }
            return this;
        }

        public int getPosition() {
            return mPosition;
        }
    }

}
