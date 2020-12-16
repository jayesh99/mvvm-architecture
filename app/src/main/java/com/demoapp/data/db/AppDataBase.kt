package com.demoapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.demoapp.data.pojo.UserEntity


@Database(entities = [UserEntity::class],version = 1,exportSchema = false)
@TypeConverters(MyTypeConverters::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun provideUserDao() : UserDao

}