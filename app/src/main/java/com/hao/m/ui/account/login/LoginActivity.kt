package com.hao.m.ui.account.login

import android.view.View
import com.hao.m.R
import com.hao.m.base.BaseActivity
import com.hao.m.ui.main.MainActivity
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor

/*
    登录界面
 */

class LoginActivity : BaseActivity(), LoginContract.View, View.OnClickListener {

    override fun onLoginResult(result: String) {
    }

    override fun resLayout() = R.layout.activity_login

    private lateinit var mPresenter: LoginContract.Presenter

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
        mPresenter.login("123", "123")
        tvLogin.setOnClickListener(this)

    }

    override fun onClick(v: View?) {

        when (v!!.id) {
            R.id.tvLogin ->
                goToMain()
        }
    }

    private fun goToMain() {
        startActivity(this@LoginActivity.intentFor<MainActivity>())
        finish()
    }

}
