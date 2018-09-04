package com.hao.m.base

import android.content.pm.ActivityInfo
import android.os.Bundle
import com.hao.m.AppManager
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
    }

    override fun onDestroy() {
        AppManager.instance.finishActivity(this)
        super.onDestroy()
    }
}