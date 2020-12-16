package com.demoapp.ui.home

import android.os.Bundle
import com.demoapp.R
import com.demoapp.rx.MY_DATA
import com.demoapp.rx.RxBus
import com.demoapp.rx.observer.ObserverWithHandler
import com.demoapp.ui.base.ToolBarActivity
import com.demoapp.utills.AESUtills
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : ToolBarActivity(), HasAndroidInjector {


    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>

    @Inject
    lateinit var rxBus: RxBus

    @Inject
    lateinit var aesUtills: AESUtills




    override fun androidInjector() = dispatchingAndroidInjector

    override fun getMainLayout(): Int = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        initNavigationSlider()

        rxBus.scribeWithBusTag<String>(MY_DATA)
            .subscribe(object : ObserverWithHandler<String>(this) {
                override fun onNext(t: String) {

                }
            })
        rxBus.scribeWithBus<String>()
            .subscribe(object : ObserverWithHandler<String>(this) {
                override fun onNext(t: String) {

                }
            })
        rxBus.scribeWithBus<Int>()
            .subscribe(object : ObserverWithHandler<Int>(this) {
                override fun onNext(t: Int) {

                }
            })
        rxBus.scribeWithBus<Any>()
            .subscribe(object : ObserverWithHandler<Any>(this) {
                override fun onNext(t: Any) {

                }
            })


    }

    private fun initNavigationSlider() {
        //init variable so we can access from here in to child class
        drawerLayout = drawerLayoutMain
    }


}