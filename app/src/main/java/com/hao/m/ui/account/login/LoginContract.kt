package com.hao.m.ui.account.login

import com.hao.m.entity.response.ZhUserEntity
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.hao.m.mvp.BasePresenter
import com.hao.m.mvp.BaseView

/**
*@author : HaoBoy
**/
interface LoginContract {

    interface Presenter : BasePresenter {

        /**登录**/
        fun login(userName :String)

    }

    interface View : BaseView<Presenter> {

        fun getActivity(): RxAppCompatActivity

        fun onLoginResult(result: ZhUserEntity)
    }
}