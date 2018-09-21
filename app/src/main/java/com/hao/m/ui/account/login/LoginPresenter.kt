package com.hao.m.ui.account.login

import com.hao.m.bridge.retrofit.callback.HttpOnNextListener
import com.hao.m.bridge.retrofit.http.HttpManager
import com.hao.m.bridge.retrofit.service.AccountService
import com.hao.m.entity.CommonResult
import com.hao.m.entity.response.ZhUserEntity
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/*
    登录界面 Presenter
 */
class LoginPresenter(val mView: LoginContract.View) : LoginContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    override fun login(user: String) {
        HttpManager.instance.apply {
            showProgress("加载中...")
            setOption(null)
            doHttpDeal(mView.getActivity(),
                    createService(AccountService::class.java)!!.login(user).apply {
                        this.bindToLifecycle(mView.getActivity())
                    }, object : HttpOnNextListener<ZhUserEntity>() {
                override fun onNext(commonResult: CommonResult<ZhUserEntity>) {
                   mView.onLoginResult(commonResult.data)
                }
            })
        }
    }
}
