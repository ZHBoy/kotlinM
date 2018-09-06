package com.hao.m.utils

import android.view.MotionEvent
import android.view.View
import android.widget.EditText

/**
 * @作者 hao
 * @创建日期 2018/3/23 15:19
 * Description: 软键盘控制
 */

object KeyBoardUtils {

    /**
     * 判断在什么情况下隐藏软键盘，点击EditText视图显示软键盘
     * @param view Incident event
     * @param event
     * @return
     */
    fun isShouldHideSoftKeyBoard(view: View?, event: MotionEvent): Boolean {
        if (view != null && view is EditText) {
            val l = intArrayOf(0, 0)
            view.getLocationInWindow(l)
            val left = l[0]
            val top = l[1]
            val bottom = top + view.height
            val right = left + view.width
            return !(event.x > left && event.x < right
                    && event.y > top && event.y < bottom)
        }
        // if the focus is EditText,ignore it;
        return false
    }
}
