package com.demoapp.data.datasource


import com.demoapp.data.api.ApiInterface
import com.demoapp.data.db.UserDao
import com.demoapp.data.pojo.ResponseData
import com.demoapp.data.pojo.UserEntity
import com.demoapp.data.repository.UserRepository
import com.demoapp.rx.observer.SingleWithOutHandler
import com.demoapp.utills.SharedPrefsManager
import com.demoapp.utills.makeThreadSafe
import io.reactivex.Single
import io.reactivex.SingleOnSubscribe
import javax.inject.Inject

class UserDataSource @Inject constructor(
    private val apiInterface: ApiInterface,
    private val userDao: UserDao,
    private val sharedPrefsManager: SharedPrefsManager
) : UserRepository {


    override fun updateUserList() {
        apiInterface.getUsers()
            .makeThreadSafe()
            .doAfterSuccess {
                insertOrUpdateUserToDb(it.data)
            }
            .subscribe(SingleWithOutHandler<ResponseData<List<UserEntity>>>())

    }

    private fun insertOrUpdateUserToDb(data: List<UserEntity>?) {
        data?.let {
            Single.create(SingleOnSubscribe<Boolean> {
                userDao.deleteAllUser()
                userDao.insertUser(data)
            }).makeThreadSafe()
                .subscribe(SingleWithOutHandler())
        }
    }


}