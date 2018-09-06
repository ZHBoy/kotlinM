package com.hao.m.ui.main.fragment

import com.hao.m.R
import com.hao.m.base.BaseFragment

class HomeFragment : BaseFragment() {
    override fun layoutResId(): Int = R.layout.fragment_home

    override fun init() {
    }

    override fun logic() {
    }

    companion object {
        fun newInstance() = HomeFragment()
    }

}