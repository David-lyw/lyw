package com.example.david.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.david.Dto.CollectionDto;
import com.example.david.lyw.R;
import com.lyw.adapter.HolderAdapter;

import java.util.List;

/**
 * Created by David on 16/12/27.
 */
public class FollowAdapter extends HolderAdapter<CollectionDto>{
    public FollowAdapter(Context context, List<CollectionDto> data) {
        super(context, R.layout.adapter_item_follow, data);
    }

    @Override
    protected RecyclerView.ViewHolder generateViewHolder(View view) {
        return new Holder(view);
    }

    @Override
    protected void bindView(RecyclerView.ViewHolder view, int position) {

    }

    class Holder extends BaseViewHolder {

        public Holder(View view) {
            super(view);
        }
    }
}
