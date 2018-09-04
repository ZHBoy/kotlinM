package com.hao.m.utils

import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.math.log

/**
 * Created by HaoBoy
 */
object TLog : AnkoLogger {

    private val isOpenDebug = true

    private val debugInfo = true
    private val debugError = true


    fun i(msg: String) {
        if (isOpenDebug && debugInfo) {
            info(msg)
        }

    }

    fun i(msg: String,  count: Int) {
        if (isOpenDebug && debugInfo) {
            if (msg.length > count) {
                val show = msg.substring(0, count)
                info(show)
                if ((msg.length - count) > count) {
                    val partLog = msg.substring(count, msg.length)
                    i(partLog, count)
                } else {
                    val surplusLog = msg.substring(count, msg.length)
                    info(surplusLog)
                }
            } else {
                info(msg)
            }
        }

    }

    fun e(msg: String) {
        if (isOpenDebug && debugError)
            error(msg)
    }


}