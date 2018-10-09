package com.hao.m.ui.main.home

import android.content.res.AssetManager
import android.support.v7.widget.LinearLayoutManager
import android.view.View.inflate
import com.hao.m.R
import com.hao.m.base.BaseFragment
import com.hao.m.bridge.retrofit.http.HttpManager
import com.hao.m.common.IntentDataDef
import com.hao.m.recyclerview.divider.SpaceItemDecoration
import com.hao.m.ui.account.login.GlideImageLoader
import com.hao.m.ui.detail.HeroDetailActivity
import com.hao.m.utils.ToastUtil
import com.youth.banner.Banner
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.intentFor
import java.util.zip.Inflater
import android.view.LayoutInflater
import android.widget.LinearLayout


class HomeFragment : BaseFragment() {

    private val PAGE_SIZE = 6

    private var mNextRequestPage = 1

    private val httpManager: HttpManager = HttpManager.instance

    private lateinit var mHomePersonalAdapter: HomePersonalAdapter

    private var dataList: java.util.ArrayList<String> = ArrayList()

    override fun layoutResId(): Int = R.layout.fragment_home

    private fun initData() {
        var start = Math.random().toInt()
        for (i in start..start + 10) {
            dataList.add(i.toString())
        }
    }

    override fun init() {
        val content = LayoutInflater.from(activity).inflate(R.layout.header_home, null) as LinearLayout
        val banner = content.findViewById<Banner>(R.id.homeBanner)
        //本地图片数据（资源文件）
        banner.setImages(getBanners())
                .setImageLoader(GlideImageLoader())
                .start()
        initData()
        mHomePersonalAdapter = HomePersonalAdapter(activity!!, dataList)

        mHomePersonalAdapter.setHeaderView(content)
        rvAddressData.layoutManager = LinearLayoutManager(activity)
        rvAddressData.addItemDecoration(SpaceItemDecoration(1))
        rvAddressData.adapter = mHomePersonalAdapter

    }

    override fun logic() {

        mHomePersonalAdapter.let {
            it.setOnItemClickListener { _, _, position ->
                ToastUtil.showCustomToast("点击了：$position")
                startActivity(activity!!.intentFor<HeroDetailActivity>(
                        IntentDataDef.HERO_INFO_KEY to dataList[position]
                ))
            }
            it.setOnLoadMoreListener({
                val isRefresh = mNextRequestPage == 1
                setData(isRefresh, dataList)
            }, rvAddressData)
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

    private fun getBanners(): List<String> {
        val banners = activity?.assets?.list("")?.asList()
        return banners!!
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