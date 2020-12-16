package com.demoapp.data.api

import com.demoapp.data.pojo.ResponseData
import com.demoapp.data.pojo.UserEntity
import io.reactivex.Single
import retrofit2.http.*

interface ApiInterface {


    @GET(URLFactory.USER_PAGE)
    fun getUsers(@Query("results") deviceToken: String="100"): Single<ResponseData<List<UserEntity>>>


}