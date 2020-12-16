package com.demoapp.di

import android.app.Application
import com.demoapp.MyApplication
import com.demoapp.di.builder.app.AppModule
import com.demoapp.di.builder.BuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [AndroidInjectionModule::class, BuildersModule::class, AppModule::class]
)
interface AppComponets {


    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application) : Builder
        
        fun build(): AppComponets

    }

    fun inject(app: MyApplication)

}