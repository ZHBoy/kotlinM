package com.hao.m.utils

import com.hao.m.KotlinMApp
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.info
import kotlin.math.log

/**
 * Created by HaoBoy
 */
object TLog : AnkoLogger {

    fun i(msg: String) {
        if(KotlinMApp.isDebug)
            info(msg)
    }

    fun i(msg: String,  count: Int) {
        if(KotlinMApp.isDebug)
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

    fun e(msg: String) {
        if(KotlinMApp.isDebug)
            error(msg)
    }

}