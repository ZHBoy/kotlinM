package com.hao.m.ui.main.message

import com.hao.m.R
import com.hao.m.base.BaseFragment
import com.hao.m.bridge.retrofit.http.HttpManager
import kotlinx.android.synthetic.main.fragment_message.*

class MessageFragment : BaseFragment() {
    private val httpManager: HttpManager = HttpManager.instance

    override fun layoutResId(): Int = R.layout.fragment_message

    override fun init() {
//        mHomePersonalAdapter = HomePersonalAdapter()

        tvHome.text = "${"我是消息页棉"+System.currentTimeMillis()}"    }

    override fun logic() {

//        httpManager.apply {
//            doHttpDeal(context!!,
//                    httpManager.createService(AccountService::class.java)!!.getAllUser(),
//                    object : HttpOnNextListener() {
//                        override fun onNext(json: String) {
//
//                        }
//                    })
//        }
    }

    companion object {
        fun newInstance() = MessageFragment()
    }

}