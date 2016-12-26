package com.example.david.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.david.lyw.R;
import com.lyw.adapter.HolderAdapter;

import java.util.List;

/**
 * Created by David on 16/12/26.
 */
public class holdertestAdapter extends HolderAdapter<String> {

    public holdertestAdapter(Context context, List<String> data) {
        super(context, R.layout.adapter_holdertest, data);
    }


    @Override
    protected RecyclerView.ViewHolder generateViewHolder(View view) {
        return new Holder(view);
    }

    @Override
    protected void bindView(RecyclerView.ViewHolder view, int var2) {
        String str = mData.get(var2);
        Holder holder = (Holder) view;
        holder.tv.setText("hahhahahah");
    }


    public class Holder extends BaseViewHolder {
        public TextView tv;
        public Holder(View itemView) {
            super(itemView);
            tv = (TextView) mConvertView.findViewById(R.id.tv1);
        }
    }
}
