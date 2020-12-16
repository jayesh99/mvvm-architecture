package com.demoapp.data.api


import com.demoapp.utills.SharedPrefsManager
import okhttp3.Interceptor
import okhttp3.Response


class HeaderAndErrorInterceptor(private val sharedPrefsManager: SharedPrefsManager) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val original = chain.request()
        val requestBuilder = original.newBuilder()

        val request = requestBuilder.build()

        val response = chain.proceed(request)

        if (response.code >= 500) {
            throw ServerException(
               "Internal server error"
            )
        }

        if (response.code >= 401) {
            throw ServerException(
               "Internal server error"
            )
                .setType(ServerException.Companion.Type.AUTH)
        }

        return response
    }

}