package com.demoapp.data.api

import java.lang.RuntimeException

class ServerException(message:String?) : RuntimeException(message)  {

    var type : Type = Type.ERROR

    fun setType(type: Type) : ServerException {
        this.type=type
        return this
    }

    companion object {

        enum class Type {
            ERROR,AUTH
        }

    }

}