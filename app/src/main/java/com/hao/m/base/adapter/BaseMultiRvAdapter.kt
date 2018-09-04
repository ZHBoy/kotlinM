package com.hao.m.base.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

import java.util.ArrayList

/**
 * Created by HaoBoy
 */

abstract class BaseMultiRvAdapter<T>(list: ArrayList<T>,
                                     context: Context,
                                     private val support: BaseMultiItemTypeSupport<T>)
    : BaseRvAdapter<T>(list, 0, context) {

    override fun getItemViewType(position: Int): Int {
        return support.getItemViewType(position, mDatas[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutId = support.getLayoutId(viewType)
        return Companion.BaseRvHolder.get(context, parent, layoutId)
    }
}
