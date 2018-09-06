package com.hao.m.base

import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.MotionEvent
import android.view.inputmethod.InputMethodManager
import com.hao.m.AppManager
import com.hao.m.utils.Density
import com.hao.m.utils.KeyBoardUtils
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity

/**
 * Created by HaoBoy
 */
abstract class BaseActivity : RxAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        base()
        super.onCreate(savedInstanceState)
        setContentView(resLayout())
        getSavedInstance(savedInstanceState)
        init()
        logic()
    }

    override fun onResume() {
        super.onResume()
    }

    override fun onPause() {
        super.onPause()
    }

    abstract fun resLayout(): Int

    abstract fun init()

    abstract fun logic()

    open fun getSavedInstance(savedInstanceState: Bundle?) {

    }

    open fun base() {
        AppManager.instance.addActivity(this)
        this.requestWindowFeature(android.view.Window.FEATURE_NO_TITLE)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        Density.setDefault(this)

    }

    override fun onDestroy() {
        AppManager.instance.finishActivity(this)
        super.onDestroy()
    }

    /**
     * 点击软键盘之外的空白处，隐藏软件盘
     *
     * @param ev
     * @return
     */
    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        if (ev.action == MotionEvent.ACTION_DOWN) {
            val v = currentFocus
            if (KeyBoardUtils.isShouldHideSoftKeyBoard(v, ev)) {

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm?.hideSoftInputFromWindow(v!!.windowToken, 0)
            }
            return super.dispatchTouchEvent(ev)
        }
        // 必不可少，否则所有的组件都不会有TouchEvent了
        return if (window.superDispatchTouchEvent(ev)) {
            true
        } else onTouchEvent(ev)
    }

}