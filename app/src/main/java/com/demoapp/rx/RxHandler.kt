package com.demoapp.rx

import io.reactivex.disposables.Disposable

interface RxHandler {

    fun onSubscribe(d: Disposable)

    fun onError(e: Throwable)

}