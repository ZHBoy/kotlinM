package com.hao.m.ui.account.login

import android.content.Context
import android.widget.ImageView
import com.hao.m.utils.GlideUtils
import com.youth.banner.loader.ImageLoader

/*轮播图数据绑定类*/
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.instance.loadHttpHeadImage(context, "file:///android_asset/$path", imageView)
    }

}