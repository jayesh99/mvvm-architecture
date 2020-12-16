package com.demoapp.rx

import android.text.TextUtils
import io.reactivex.*
import io.reactivex.functions.Function
import io.reactivex.subjects.PublishSubject


class RxBus {

    private val bus = PublishSubject.create<Any>()

    private val tagBus = PublishSubject.create<Pair<String, Any>>()


    fun <T> scribeWithBus(): Observable<T> {

        return bus.flatMap(Function<Any, Observable<T>> {
            return@Function Observable.create<T> { e ->
                val data: T = it as T
                e.onNext(data)
            }
        })

    }


    fun <T> scribeWithBusTag(tag: String): Observable<T> {
        return tagBus.flatMap(Function<Pair<String, Any>, Observable<T>> {
            return@Function Observable.create<T> { e ->
                val data: T = it.second as T
                if (tag.equals(it.first, true))
                    e.onNext(data)
            }
        })

    }

    fun post(data: Any, tag: String = "") {
        bus.onNext(data)
        if (!TextUtils.isEmpty(tag))
            tagBus.onNext(Pair(tag, data))
    }


}
