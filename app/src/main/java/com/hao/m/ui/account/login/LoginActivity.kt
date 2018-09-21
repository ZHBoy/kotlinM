package com.hao.m.ui.account.login

import android.view.View
import com.hao.m.R
import com.hao.m.base.BaseActivity
import com.hao.m.entity.response.ZhUserEntity
import com.hao.m.ui.main.MainActivity
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.intentFor

/*
    登录界面
 */

class LoginActivity : BaseActivity(), LoginContract.View, View.OnClickListener {

    override fun onLoginResult(result: ZhUserEntity) {
        startActivity(this@LoginActivity.intentFor<MainActivity>())
        finish()
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
//        val username = etUserName.text.toString().trim()
//        if (!TextUtils.isEmpty(username)){
//            mPresenter.login(username)
//        }
    }

}
