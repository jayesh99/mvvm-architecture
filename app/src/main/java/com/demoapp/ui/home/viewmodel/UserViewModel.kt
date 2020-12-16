package com.demoapp.ui.home.viewmodel


import androidx.databinding.ObservableField
import com.demoapp.data.db.UserDao
import com.demoapp.data.repository.UserRepository
import com.demoapp.ui.base.BaseViewModel
import javax.inject.Inject

class UserViewModel @Inject constructor(private val userRepository: UserRepository, val userDao: UserDao) :

    BaseViewModel() {


    val errorMessage = ObservableField<String>()


    init {
        userRepository.updateUserList()
    }


    fun getAllUserList() = userDao.getAllList()


}