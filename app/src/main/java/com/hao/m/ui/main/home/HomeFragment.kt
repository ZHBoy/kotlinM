package com.hao.m.ui.main.home

import android.support.v7.widget.LinearLayoutManager
import com.hao.m.R
import com.hao.m.base.BaseFragment
import com.hao.m.bridge.retrofit.http.HttpManager
import com.hao.m.recyclerview.divider.SpaceItemDecoration
import com.hao.m.utils.ToastUtil
import kotlinx.android.synthetic.main.fragment_home.*
import kotlin.collections.ArrayList

class HomeFragment : BaseFragment() {

    private val PAGE_SIZE = 6

    private var mNextRequestPage = 1

    private val httpManager: HttpManager = HttpManager.instance

    private lateinit var mHomePersonalAdapter: HomePersonalAdapter

    private var dataList: java.util.ArrayList<String> = ArrayList()

    override fun layoutResId(): Int = R.layout.fragment_home

    private fun initData() {
        var start = Math.random().toInt()
        for (i in start..start+10){
            dataList.add(i.toString())
        }
    }

    override fun init() {
        initData()
        mHomePersonalAdapter = HomePersonalAdapter(activity!!,dataList)
        //默认第一次加载会进入回调，如果不需要可以配置
        rvAddressData.layoutManager = LinearLayoutManager(activity)
        rvAddressData.addItemDecoration(SpaceItemDecoration(1))
        rvAddressData.adapter = mHomePersonalAdapter

    }

    override fun logic() {

        mHomePersonalAdapter.let {
            it.setOnItemClickListener { _, _, position ->
                ToastUtil.showCustomToast("点击了：$position")
            }
            it.setOnLoadMoreListener({
                val isRefresh = mNextRequestPage == 1
                setData(isRefresh,dataList)
            },rvAddressData)
        }


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

    private fun setData(isRefresh: Boolean, data: List<String>?) {
        mNextRequestPage++
        val size = data?.size ?: 0
        //是否是刷新
        if (isRefresh) {
            mHomePersonalAdapter.setNewData(data)
        } else {
            if (size > 0) {
                mHomePersonalAdapter.addData(data!!)
            }
        }
        //是否是最后一页
        if (size < PAGE_SIZE) {
            //第一页如果不够一页就不显示没有更多数据布局
            mHomePersonalAdapter.loadMoreEnd(isRefresh)

        } else {
            mHomePersonalAdapter.loadMoreComplete()
        }
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}