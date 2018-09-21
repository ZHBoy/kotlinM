package com.hao.m.bridge.retrofit.callback

import com.hao.m.bridge.retrofit.exception.ApiErrorModel
import com.hao.m.entity.CommonResult
import com.hao.m.utils.TLog
import com.hao.m.utils.ToastUtil

/**
 * Created by HaoBoy
 */
abstract class HttpOnNextListener<T> {


    open fun onError(statusCode: Int, apiErrorModel: ApiErrorModel?) {
        apiErrorModel?.message?.let {
            ToastUtil.showToast(it)
            TLog.i(it)
        }

    }

    open fun onCache(commonResult: CommonResult<T>) {
        onNext(commonResult)
    }

    abstract fun onNext(commonResult: CommonResult<T>)


}