package com.hao.m.ui.main.recommend

import android.support.v7.widget.LinearLayoutManager
import com.hao.m.R
import com.hao.m.base.BaseFragment
import com.hao.m.bridge.retrofit.http.HttpManager
import kotlinx.android.synthetic.main.fragment_recommend.*
import java.util.*

class RecommendFragment : BaseFragment() {
    private val httpManager: HttpManager = HttpManager.instance
    private lateinit var recommendAdapter: RecommendAdapter

    override fun layoutResId(): Int = R.layout.fragment_recommend

    private var dataList: ArrayList<String> = ArrayList()

    private fun initData() {
        var start = Math.random().toInt()
        for (i in start..start+10){
            dataList.add(i.toString())
        }
    }
    override fun init() {
        recommendAdapter = RecommendAdapter(Arrays.asList("1","2","3","4","5","6"))
        rvRecommend.layoutManager = LinearLayoutManager(activity)
        rvRecommend.adapter = recommendAdapter

    }

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
        fun newInstance() = RecommendFragment()
    }

}