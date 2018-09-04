package com.hao.m.bridge.loadimg.glide

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.hao.m.R
import com.hao.m.bridge.loadimg.LoadImageStrategy
import java.io.File


/**
 * Created by HaoBoy
 */
class GlideLoad : LoadImageStrategy {

    override fun loadImage(imageView: ImageView, resId: Int, options: RequestOptions?) {
        Glide.with(imageView.context)
                .setDefaultRequestOptions(options ?: RequestOptions().placeholder(R.drawable.placeholder))
                .load(resId)
                .into(imageView)
    }

    override fun loadImage(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
                .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.placeholder))
                .load(url)
                .into(imageView)
    }

    override fun loadImage(imageView: ImageView, file: File) {
        Glide.with(imageView.context)
                .setDefaultRequestOptions(RequestOptions().placeholder(R.drawable.placeholder))
                .load(file)
                .into(imageView)
    }

    override fun loadAvatar(imageView: ImageView, url: String) {
        Glide.with(imageView.context)
                .load(url)
                .apply(RequestOptions.bitmapTransform(CircleCrop()))
                .into(imageView)
    }
}