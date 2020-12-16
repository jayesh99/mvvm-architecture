package com.demoapp.utills

import com.demoapp.data.api.ServerException
import com.demoapp.data.pojo.ResponseData
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Single<T>.makeThreadSafe() = this.subscribeOn(Schedulers.io())
    .observeOn(AndroidSchedulers.mainThread())
    .doOnSuccess {
        if (it is ResponseData<*> &&it.info?.results==0)
            throw ServerException("Something went wrong")
    }

fun String.zzzz() = this.substring(5,21)