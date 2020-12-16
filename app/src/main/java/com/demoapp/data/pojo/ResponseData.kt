package com.demoapp.data.pojo

import com.google.gson.annotations.SerializedName

data class ResponseData<T>(


    @SerializedName("results")
    var data: T? = null,
    var info: Info? = null
)
{
     data class Info(
         val results :Int =0,
         val page :Int =0,
         val version :Double =1.0
     )

}