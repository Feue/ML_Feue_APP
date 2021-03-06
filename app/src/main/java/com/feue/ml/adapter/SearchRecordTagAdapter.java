package com.feue.ml.adapter;

import androidx.annotation.NonNull;

import com.feue.ml.R;
import com.feue.ml.db.entity.SearchRecord;
import com.xuexiang.xui.adapter.recyclerview.BaseRecyclerAdapter;
import com.xuexiang.xui.adapter.recyclerview.RecyclerViewHolder;

public class SearchRecordTagAdapter extends BaseRecyclerAdapter<SearchRecord> {

    @Override
    protected int getItemLayoutId(int viewType) {
        return R.layout.adapter_search_record_tag_item;
    }

    @Override
    protected void bindData(@NonNull RecyclerViewHolder holder, int position, SearchRecord item) {
        if (item != null) {
            holder.text(R.id.tv_tag, item.getContent());
        }
    }
}