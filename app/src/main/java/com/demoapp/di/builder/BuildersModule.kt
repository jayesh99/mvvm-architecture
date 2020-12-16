package com.demoapp.di.builder

import com.demoapp.ui.SplashActivity
import com.demoapp.ui.home.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BuildersModule {

    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributesMainActivity(): MainActivity


    @ContributesAndroidInjector(modules = [FragmentBuildersModule::class])
    abstract fun contributesSplashActivity(): SplashActivity


}