package com.hao.m.bridge.retrofit.callback

import android.content.Context
import android.text.TextUtils
import com.google.gson.Gson
import com.hao.m.AppManager
import com.hao.m.bridge.retrofit.exception.ApiErrorModel
import com.hao.m.bridge.retrofit.exception.ApiErrorType
import com.hao.m.entity.CommonResult
import com.hao.m.ui.account.login.LoginActivity
import com.hao.m.utils.TLog
import com.hao.m.widgets.LoadingDialog
import com.xfs.fsyuncai.bridge.retrofit.http.RequestOption
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.jetbrains.anko.intentFor
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * Created by HaoBoy
 */
class ApiResponse<T>(private val context: Context,
                  private val msg: String,
                  private val option: RequestOption,
                  private val listener: HttpOnNextListener<T>?) : Observer<CommonResult<T>> {

    override fun onComplete() {
        LoadingDialog.dissmiss()
    }

    override fun onSubscribe(d: Disposable) {
        if (option.isShowProgress) LoadingDialog.show(context, msg)
    }

    override fun onNext(t: CommonResult<T>) {
    }

    override fun onError(error: Throwable) {
        LoadingDialog.dissmiss()
        errorDo(error)
    }

    //服务器异常
    private fun errorDo(e: Throwable) {
        TLog.i("http_error>>>>>>${e.message}")
        if (e is HttpException) {

            val apiErrorModel: ApiErrorModel? = when (e.code()) {
                ApiErrorType.INTERNAL_SERVER_ERROR.code ->
                    ApiErrorType.INTERNAL_SERVER_ERROR.getApiErrorModel(context)
                ApiErrorType.BAD_GATEWAY.code ->
                    ApiErrorType.BAD_GATEWAY.getApiErrorModel(context)
                ApiErrorType.NOT_FOUND.code ->
                    ApiErrorType.NOT_FOUND.getApiErrorModel(context)
                ApiErrorType.PARSING_FAILURE.code ->
                    ApiErrorType.PARSING_FAILURE.getApiErrorModel(context)
                else -> otherError(e)
            }

            listener?.onError(e.code(), apiErrorModel)
            return
        }

        val apiErrorType: ApiErrorType = when (e) {  //发送网络问题或其它未知问题，请根据实际情况进行修改
            is UnknownHostException -> ApiErrorType.NETWORK_NOT_CONNECT
            is ConnectException -> ApiErrorType.NETWORK_NOT_CONNECT
            is SocketTimeoutException -> ApiErrorType.CONNECTION_TIMEOUT
            else -> ApiErrorType.UNEXPECTED_ERROR
        }
        listener?.onError(apiErrorType.code, apiErrorType.getApiErrorModel(context))
    }

    private fun otherError(e: HttpException): ApiErrorModel? {
        var model: ApiErrorModel? = null
        try {
            model = Gson().fromJson(e.response().errorBody()?.charStream(), ApiErrorModel::class.java)

        } catch (e: Exception) {
            TLog.i(e.toString())
        }
        return model
    }
}