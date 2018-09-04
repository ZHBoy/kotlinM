package com.hao.m.presenter

import com.hao.m.bridge.retrofit.callback.HttpOnNextListener
import com.hao.m.bridge.retrofit.http.HttpManager
import com.hao.m.bridge.retrofit.service.AccountService
import com.trello.rxlifecycle2.kotlin.bindToLifecycle

/*
    登录界面 Presenter
 */
class LoginPresenter(val mView: LoginContract.View) : LoginContract.Presenter {

    init {
        mView.setPresenter(this)
    }

    override fun login(userId: String, pwd: String) {
        HttpManager.instance.apply {
            showProgress("加载中...")
            setOption(null)
            doHttpDeal(mView.getActivity(),
                    createService(AccountService::class.java)!!.login(userId,pwd).apply {
                        this.bindToLifecycle(mView.getActivity())
                    },
                    object: HttpOnNextListener(){
                        override fun onNext(json: String) {
                            mView.onLoginResult(json)
                        }
                    })
        }    }
}
