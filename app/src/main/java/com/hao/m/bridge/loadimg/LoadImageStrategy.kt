package com.hao.m.bridge.loadimg

import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import java.io.File

/**
 * Created by HaoBoy
 */
interface LoadImageStrategy {

    fun loadAvatar(imageView: ImageView, url: String)

    fun loadImage(imageView: ImageView, url: String)

    fun loadImage(imageView: ImageView, file: File)

    fun loadImage(imageView: ImageView, resId: Int, options: RequestOptions? = null)
}