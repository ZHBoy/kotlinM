package com.hao.m.utils

import android.view.Gravity
import android.widget.Toast
import com.hao.m.KotlinMApp
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.toast


/**
 * Created by HaoBoy on 2018/6/16.
 */
object ToastUtil : AnkoLogger {

    private var toast: Toast? = null

    fun showToast(msg: String) {
        KotlinMApp.context().toast(msg)
    }

    fun showCustomToast(msg: String): Toast {
        if (toast == null) {
            toast = Toast.makeText(UIUtils.context(), msg, Toast.LENGTH_SHORT)
        } else {
            toast!!.setText(msg)
        }
        toast!!.show()
        toast!!.setGravity(Gravity.CENTER, 0, 0)
        return toast!!
    }

//    /**
//     * 带图片的toast
//     */
//    fun showIconToast(msg: String,icon:Int) {
//        val view = LayoutInflater.from(UIUtils.context()).inflate(R.layout.toast_with_icon,
//                null)
//        val tvMessage = view.findViewById<TextView>(R.id.tvMessage)
//        tvMessage.text = msg
//        val image = view.findViewById<ImageView>(R.id.tIcon)
//        if (icon != 0){image.setImageResource(icon)}
//        val toastStart = Toast(UIUtils.context())
//
//        toastStart.setGravity(Gravity.CENTER, 0, 0)
//        toastStart.duration = Toast.LENGTH_SHORT
//        toastStart.view = view
//        toastStart.show()
//    }

}