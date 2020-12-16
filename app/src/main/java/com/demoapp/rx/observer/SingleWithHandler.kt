package com.demoapp.rx.observer

import com.demoapp.rx.RxHandler
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

 abstract class SingleWithHandler <T>  constructor(val handler : RxHandler) : SingleObserver<T> {


    abstract override fun onSuccess(t: T)

    override fun onSubscribe(d: Disposable) {
        handler.onSubscribe(d)
    }

    override fun onError(e: Throwable) {
        handler.onError(e)
    }

}