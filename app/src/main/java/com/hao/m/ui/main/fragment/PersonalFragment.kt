package com.hao.m.ui.main.fragment

import com.hao.m.R
import com.hao.m.base.BaseFragment

class PersonalFragment : BaseFragment() {
    override fun layoutResId(): Int = R.layout.fragment_personal

    override fun init() {
    }

    override fun logic() {
    }

    companion object {
        fun newInstance() = PersonalFragment()
    }

}