package com.hao.m

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Resources
import android.support.multidex.MultiDexApplication
import com.hao.m.utils.Density

/**
 * Created by HaoBoy on 2018/6/12.
 */
class KotlinMApp : MultiDexApplication() {

    companion object {

        const val isDebug = true

        @SuppressLint("StaticFieldLeak")
        @JvmField
        var cxt: Context? = null

        @JvmField
        var res: Resources? = null

        fun context(): Context {
            return cxt!!
        }

        fun resources(): Resources {
            return res!!
        }

    }

    override fun onCreate() {
        super.onCreate()
        cxt = applicationContext
        res = cxt!!.resources

        Density.setDensity(this)
    }
}
