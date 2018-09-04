package com.hao.m.base.adapter

/**
 * Created by HaoBoy
 */

interface BaseMultiItemTypeSupport<T> {
    fun getLayoutId(itemType: Int): Int

    fun getItemViewType(position: Int, t: T): Int
}