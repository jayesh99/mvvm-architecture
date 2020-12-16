package com.demoapp.di.builder

import com.demoapp.ui.home.fragment.UserFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Suppress("unused")
@Module
abstract class FragmentBuildersModule {


    @ContributesAndroidInjector
    abstract fun contributePageFragment(): UserFragment

}
