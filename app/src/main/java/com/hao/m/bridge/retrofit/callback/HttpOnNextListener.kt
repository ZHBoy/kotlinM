package com.hao.m.bridge.retrofit.callback

import com.hao.m.bridge.retrofit.exception.ApiErrorModel
import com.hao.m.utils.TLog
import com.hao.m.utils.ToastUtil

/**
 * Created by HaoBoy
 */
abstract class HttpOnNextListener {


    open fun onError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
        apiErrorModel?.message?.let {
            ToastUtil.showToast(it)
            TLog.i(it)
        }

    }

    open fun onCache(jsonResponse: String) {
        onNext(jsonResponse)
    }

    abstract fun onNext(json: String)


}