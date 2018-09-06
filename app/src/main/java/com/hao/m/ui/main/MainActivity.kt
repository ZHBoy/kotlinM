package com.hao.m.ui.main

import com.hao.m.R
import com.hao.m.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.view.Gravity
import com.ashokvarma.bottomnavigation.BottomNavigationItem
import com.ashokvarma.bottomnavigation.BottomNavigationBar
import com.ashokvarma.bottomnavigation.ShapeBadgeItem
import com.ashokvarma.bottomnavigation.TextBadgeItem
import com.hao.m.extend.addFragment
import com.hao.m.extend.replaceFragment
import com.hao.m.ui.main.fragment.HomeFragment
import com.hao.m.ui.main.fragment.PersonalFragment
import org.jetbrains.anko.toast


/**
 *@author : HaoBoy
 *@date : 2018/9/6
 *@description : 主activity
 **/
class MainActivity : BaseActivity(), BottomNavigationBar.OnTabSelectedListener {

    private val homeFragment: HomeFragment = HomeFragment.newInstance()

    private val personalFragment: PersonalFragment = PersonalFragment.newInstance()

    private lateinit var numberBadgeItem: TextBadgeItem

    private lateinit var shapeBadgeItem: ShapeBadgeItem


    override fun resLayout(): Int = R.layout.activity_main

    override fun init() {
        setNumber()
        setBottomNavigationBar()
        bottomNavigationBar.setTabSelectedListener(this)
        setFragment()
    }

    override fun logic() {
    }

    private fun setBottomNavigationBar(){
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
        addFragment(homeFragment, R.id.frameLayout)
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
        when (position) {
            0 -> replaceHome()
            3 -> replacePersonal()
        }
    }

    private fun replacePersonal() {
        if (!personalFragment.isVisible) {
            replaceFragment(personalFragment, R.id.frameLayout)
        } else {
            toast("首页已经显示")
        }
    }

    private fun replaceHome() {
        if (!homeFragment.isVisible) {
            replaceFragment(homeFragment, R.id.frameLayout)
        } else {
            toast("个人中心已经显示")
        }
    }

}
