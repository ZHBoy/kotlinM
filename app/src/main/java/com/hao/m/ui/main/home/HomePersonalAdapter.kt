package com.hao.m.ui.main.home

import android.app.Activity
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.hao.m.R

import java.util.ArrayList


/**
 * Created by HaoBoy on 2018/8/15.
 */

class HomePersonalAdapter(activity:Activity,data: ArrayList<String>?) : BaseQuickAdapter<String, BaseViewHolder>(R.layout.item_user_show,data) {

    override fun convert(helper: BaseViewHolder, item: String) {
        helper.setText(R.id.tvUserName,item)
    }
}
