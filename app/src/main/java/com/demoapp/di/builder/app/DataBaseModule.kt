package com.demoapp.di.builder.app

import android.app.Application
import androidx.room.Room
import com.demoapp.data.db.AppDataBase
import com.demoapp.data.db.UserDao
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataBaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(appContext: Application, @Named("db_name")dbName:String): AppDataBase =
        Room.databaseBuilder(appContext, AppDataBase::class.java,dbName).build()


    @Provides
    @Singleton
    fun provideUserDao(myAppDataBase: AppDataBase):UserDao = myAppDataBase.provideUserDao()

}