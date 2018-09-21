package com.hao.m.ui.main.recommend

import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hao.m.R
/**
*@author : HaoBoy
*@date : 2018/9/21
*@description : 发现页adapter
**/
class RecommendAdapter(data: List<String>) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_user_show, data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.tvUserName, item)
    }
}
