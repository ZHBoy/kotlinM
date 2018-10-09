package com.hao.m.utils

import android.content.Context
import android.widget.ImageView
import com.hao.m.GlideApp
import com.hao.m.R
import com.hao.m.widgets.CircularBitmapImageViewTarget


/**
*@author : HaoBoy
*@date : 2018/9/27
*@description :glide图片加载，单例
**/
class GlideUtils private constructor() {

    companion object {
        val instance: GlideUtils by lazy { GlideUtils() }
    }

    /**
     * 圆角图片
     * @param context
     * @param url
     * @param imageView
     */
    fun loadShapeImage(context: Context, url: String, imageView: ImageView) {

        val bitmapRequestBuilder = GlideApp.with(context)
                .asBitmap()//指定Bitmap类型的RequestBuilder
                .load(url)//网络URL
                .error(R.drawable.icon_with_no_thume)//异常图片
                .placeholder(R.drawable.icon_with_no_thume)//占位图片
                .fallback(R.drawable.icon_with_no_thume)//当url为空时，显示图片

        //在RequestBuilder<Bitmap> 中使用自定义的ImageViewTarget
        bitmapRequestBuilder.into(CircularBitmapImageViewTarget(context, imageView))
    }

    /**
     * 圆角头像
     * @param context
     * @param url
     * @param imageView
     */
    fun loadShapeHeadImage(context: Context, url: String, imageView: ImageView) {

        val bitmapRequestBuilder = GlideApp.with(context)
                .asBitmap()//指定Bitmap类型的RequestBuilder
                .load(url)//网络URL
                .error(R.drawable.icon_default_head)//异常图片
                .placeholder(R.drawable.icon_default_head)//占位图片
                .fallback(R.drawable.icon_default_head)//当url为空时，显示图片

        //在RequestBuilder<Bitmap> 中使用自定义的ImageViewTarget
        bitmapRequestBuilder.into(CircularBitmapImageViewTarget(context, imageView))
    }

    /**
     * 获取网络图片
     * @param context
     * @param url
     * @param imageView
     */
    fun loadHttpImage(context: Context, url: String, imageView: ImageView) {

        GlideApp
                .with(context)
                .load(url)
                .dontAnimate()
                .error(R.drawable.icon_with_no_thume)//异常图片
                .placeholder(R.drawable.icon_with_no_thume)//占位图片
                .fallback(R.drawable.icon_with_no_thume)//当url为空时，显示图片
                .centerCrop()
                .into(imageView)
    }

    /**
     * 获取网络图片
     * @param context
     * @param url
     * @param imageView
     */
    fun loadHttpHeadImage(context: Context, url: String, imageView: ImageView) {

        GlideApp
                .with(context)
                .load(url)
                .error(R.drawable.icon_default_head)//异常图片
                .placeholder(R.drawable.icon_default_head)//占位图片
                .fallback(R.drawable.icon_default_head)//当url为空时，显示图片
                .centerCrop()
                .into(imageView)
    }

}
