package com.hao.m.bridge.rx.timer

import android.util.Log

/**
 * Created by HaoBoy
 */
abstract class TimerListener {

    abstract fun onSuccess(value: Long)

    abstract fun onCompleted()

    open fun onError(throwable: Throwable) {
        Log.e("rxTimer","timer error >>>>>>>>>> " + throwable.message)
    }

}