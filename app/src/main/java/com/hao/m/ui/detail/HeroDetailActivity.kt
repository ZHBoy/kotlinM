package com.hao.m.ui.detail

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.view.View
import android.webkit.*
import com.hao.m.R
import com.hao.m.base.BaseActivity
import com.hao.m.widgets.LoadingDialog
import kotlinx.android.synthetic.main.activity_hero_detail.*

class HeroDetailActivity : BaseActivity() {

    private val url = "http://p.codekk.com/"
    override fun resLayout(): Int = R.layout.activity_hero_detail

    @SuppressLint("SetJavaScriptEnabled")
    override fun init() {
        val webSettings = tbsContent.settings
        webSettings.javaScriptEnabled = true//让webView支持JS

        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.domStorageEnabled = true
        webSettings.setAppCacheEnabled(false)
        webSettings.blockNetworkImage = false//解决图片不显示
//        tbsContent.addJavascriptInterface(JsInterface(this), resources.getString(R.string.webview_js_javainterface))
        loadUrl()

    }

    override fun logic() {

    }

    private fun loadUrl() {

        tbsContent.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                loadingProgress.visibility = View.VISIBLE
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            //无网时调用
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)
                tbsContent.visibility = View.GONE
            }
        }

        tbsContent.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult): Boolean {
                if (isFinishing) {
                    result.cancel()
                    return true
                }
                return true
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    loadingProgress.visibility = View.GONE
                } else {
                    if (View.GONE == loadingProgress.visibility && newProgress > 0) {
                        loadingProgress.visibility = View.VISIBLE
                    }
                    loadingProgress.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
            }
        }
    }
}