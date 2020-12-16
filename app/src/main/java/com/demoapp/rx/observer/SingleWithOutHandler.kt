package com.demoapp.rx.observer

import com.demoapp.utills.Logger
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class SingleWithOutHandler<T> : SingleObserver<T> {


    override fun onSuccess(t: T) {
        Logger.log(">>>>>>>>>>>>>>>>>> onSuccess")
    }

    override fun onSubscribe(d: Disposable) {
        //No more in user
    }

    override fun onError(e: Throwable) {
        Logger.log(">>>>>>>>>>>>>>>>>> onError" + e.message)
    }

}