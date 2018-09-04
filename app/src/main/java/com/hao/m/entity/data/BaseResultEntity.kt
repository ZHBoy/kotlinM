package com.hao.m.entity.data

/**
 * Created by HaoBoy
 */
data class BaseResultEntity<T>(var errorCode: Int, var msg: String = "错误", var data: T)