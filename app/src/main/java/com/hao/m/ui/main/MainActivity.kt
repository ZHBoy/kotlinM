package com.hao.m.ui.main

import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.Gravity
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.hao.m.R
import com.hao.m.adapter.NoDestroyViewPagerAdapter
import com.hao.m.base.BaseActivity
import com.hao.m.ui.main.home.HomeFragment
import com.hao.m.ui.main.message.MessageFragment
import com.hao.m.ui.main.personal.PersonalFragment
import com.hao.m.ui.main.recommend.RecommendFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


/**
 *@author : HaoBoy
 *@date : 2018/9/6
 *@description : 主activity
 **/
class MainActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener , ViewPager.OnPageChangeListener{

    private val fragments: ArrayList<Fragment> =  ArrayList()

    private val homeFragment: HomeFragment = HomeFragment.newInstance()
    private val recommendFragment: RecommendFragment = RecommendFragment.newInstance()
    private val messageFragment: MessageFragment = MessageFragment.newInstance()
    private val personalFragment: PersonalFragment = PersonalFragment.newInstance()

    private var noDestroyViewPagerAdapter: NoDestroyViewPagerAdapter? = null

    private lateinit var numberBadgeItem: TextBadgeItem

    private lateinit var shapeBadgeItem: ShapeBadgeItem


    override fun resLayout(): Int = R.layout.activity_main

    override fun init() {
        setNumber()
        setBottomNavigationBar()
        setFragment()
    }

    override fun logic() {
        bottomNavigationBar.setTabSelectedListener(this)
        viewPager.addOnPageChangeListener(this)
    }

    private fun setBottomNavigationBar() {
        bottomNavigationBar.setMode(BottomNavigationBar.MODE_FIXED)
                .setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_STATIC)

        bottomNavigationBar.activeColor = R.color.color_orange_low//选中颜色 图标和文字
//                .setInActiveColor("#8e8e8e")//默认未选择颜色
//                .setBarBackgroundColor(R.color.white);//默认背景色

        bottomNavigationBar
                .addItem(BottomNavigationItem(R.drawable.ic_home_white_24dp, "首页").setBadgeItem(shapeBadgeItem))
                .addItem(BottomNavigationItem(R.drawable.ic_book_white_24dp, "发现"))
                .addItem(BottomNavigationItem(R.drawable.ic_launch_white_24dp, "分类").setBadgeItem(numberBadgeItem))
                .addItem(BottomNavigationItem(R.drawable.ic_github_circle_white_24dp, "我的"))
                .setFirstSelectedPosition(0)//设置默认选择的按钮
                .initialise()//所有的设置需在调用该方法前完成
    }

    private fun setFragment() {
        fragments.add(homeFragment)
        fragments.add(recommendFragment)
        fragments.add(messageFragment)
        fragments.add(personalFragment)
        noDestroyViewPagerAdapter = NoDestroyViewPagerAdapter(supportFragmentManager)
        noDestroyViewPagerAdapter!!.setItems(fragments)
        viewPager.adapter = noDestroyViewPagerAdapter
    }

    private fun setNumber() {
        numberBadgeItem = TextBadgeItem()
                .setBorderWidth(4)
                .setBackgroundColorResource(R.color.color_blue)
                .setText("" + 2)
                .setHideOnSelect(true)

        shapeBadgeItem = ShapeBadgeItem()
                .setShape(ShapeBadgeItem.SHAPE_STAR_4_VERTICES)
                .setShapeColorResource(R.color.color_green)
                .setGravity(Gravity.TOP or Gravity.END)
                .setHideOnSelect(true)
    }

    override fun onTabReselected(position: Int) {}

    override fun onTabUnselected(position: Int) {}

    override fun onTabSelected(position: Int) {
        viewPager.currentItem = position
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        when (position) {
            0 -> bottomNavigationBar.selectTab(0)
            1 -> bottomNavigationBar.selectTab(1)
            2 -> bottomNavigationBar.selectTab(2)
            3 -> bottomNavigationBar.selectTab(3)
        }
    }

}
