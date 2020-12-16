package com.demoapp.rx.observer

import com.demoapp.utills.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class ObserverWithOutHandler<T> : Observer<T> {

    override fun onComplete() {
        Logger.log("On Complete")
    }


    override fun onNext(t: T) {
        Logger.log(">>>>>>>>>>>>>>>>>> onSuccess")
    }

    override fun onSubscribe(d: Disposable) {
        //No more in user
    }

    override fun onError(e: Throwable) {
        Logger.log(">>>>>>>>>>>>>>>>>> onError" + e.message)
    }

}