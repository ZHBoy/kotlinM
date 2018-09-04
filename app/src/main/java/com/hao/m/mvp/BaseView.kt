package com.hao.m.mvp

/**
 * Created by HaoBoy on 2018/6/12.
 */
interface BaseView<in T> {

    fun setPresenter(presenter : T)

}