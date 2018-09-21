package com.hao.m.ui.main.recommend;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.hao.m.R;

import java.util.List;

public class RecommendAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public RecommendAdapter(List data) {
        super(R.layout.item_user_show, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.tvUserName, item);
    }
}
