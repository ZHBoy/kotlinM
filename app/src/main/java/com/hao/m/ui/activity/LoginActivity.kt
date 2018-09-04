package com.hao.m.ui.activity

import com.hao.m.R
import com.hao.m.base.BaseActivity
import com.hao.m.presenter.LoginContract
import com.hao.m.presenter.LoginPresenter
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import com.trello.rxlifecycle2.components.support.RxFragment
import java.util.*

/*
    登录界面
 */

class LoginActivity: BaseActivity(), LoginContract.View{
    override fun onLoginResult(result: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun resLayout() = R.layout.activity_login

    private lateinit var mPresenter:LoginContract.Presenter

    override fun setPresenter(presenter: LoginContract.Presenter) {
        mPresenter = presenter
    }

    override fun getActivity(): RxAppCompatActivity {
        return this
    }
    override fun init() {
        LoginPresenter(this@LoginActivity)
    }

    override fun logic() {
        mPresenter.login("123","123")
    }

}
