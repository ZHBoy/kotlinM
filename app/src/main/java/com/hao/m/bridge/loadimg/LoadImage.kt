package com.hao.m.bridge.loadimg

import com.hao.m.bridge.loadimg.glide.GlideLoad

/**
 * Created by HaoBoy
 */
class LoadImage private constructor(){

    private var loadImageStrategy: LoadImageStrategy = GlideLoad()

    companion object {
        val instance: LoadImageStrategy by lazy { LoadImage().loadImageStrategy }
    }
}