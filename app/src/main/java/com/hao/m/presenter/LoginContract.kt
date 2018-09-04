package com.hao.m.presenter

import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.hao.m.mvp.BasePresenter
import com.hao.m.mvp.BaseView

/**
*@author : HaoBoy
**/
interface LoginContract {

    interface Presenter : BasePresenter {

        /**登录**/
        fun login(userId: String, pwd: String)

    }

    interface View : BaseView<Presenter> {

        fun getActivity(): RxAppCompatActivity

        fun onLoginResult(result: String)
    }
}