package com.demoapp


import android.app.Application
import android.content.Context
import com.demoapp.di.AppInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import javax.inject.Inject

class MyApplication : Application(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    override fun onCreate() {
        super.onCreate()

        appContext =applicationContext

        AppInjector.init(this)

    }

    override fun androidInjector() = dispatchingAndroidInjector



    companion object {

        lateinit var appContext: Context
    }

}