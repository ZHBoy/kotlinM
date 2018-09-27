package com.hao.m.ui.account.login

import android.content.Context
import android.widget.ImageView

import com.youth.banner.loader.ImageLoader

/*轮播图数据绑定类*/
class GlideImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {

        //        Glide.with(context).load(path).into(imageView);
        //        Uri uri = Uri.parse((String) path);
        //        imageView.setImageURI(uri);

        imageView.setImageResource(path as Int)
    }

}