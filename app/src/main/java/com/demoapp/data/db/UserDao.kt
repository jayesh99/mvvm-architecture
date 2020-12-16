package com.demoapp.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.demoapp.data.pojo.UserEntity


@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(vararg user: UserEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertUser(users: List<UserEntity>)

    @Delete
    fun deleteUser(user: UserEntity)

    @Query("Delete from UserEntity")
    fun deleteAllUser()

    @Update
    fun updateUser(user: UserEntity)

    @Query("Select * from UserEntity")
    fun getAllList() : LiveData<List<UserEntity>>

}