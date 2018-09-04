package com.hao.m.bridge.rx.timer

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import io.reactivex.disposables.CompositeDisposable


/**
 * Created by HaoBoy
 */
class RxTimer {


    private var disposables: CompositeDisposable = CompositeDisposable()

    fun countDown(count: Long, listener: TimerListener?) {
        Observable.interval(0, 1, TimeUnit.SECONDS)

                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t: Long -> count - t }
                .take(count)
                .subscribe({ listener?.onSuccess(it) },
                        { listener?.onError(it) },
                        { listener?.onCompleted() },
                        { disposables.add(it) })

    }


    fun countUp(listener: TimerListener?) {
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map { t: Long -> t + 1 }
                .subscribe({ listener?.onSuccess(it) },
                        { listener?.onError(it) },
                        { listener?.onCompleted() },
                        { disposables.add(it) })
    }

    fun finish() {
        disposables.clear()
    }

}