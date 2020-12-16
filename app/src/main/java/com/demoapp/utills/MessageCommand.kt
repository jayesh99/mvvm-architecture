package com.maan.mandir.utills

import com.demoapp.MyApplication


sealed class MessageCommand {


    data class Info(
        val message: String
    ) : MessageCommand() {
        constructor(message: Int) : this(MyApplication.appContext.getString(message))
    }

    data class Error (
        val message: String
    )  : MessageCommand()  {
        constructor(message: Int) : this(MyApplication.appContext.getString(message))
    }


    data class Warn(
        val message: String
    ) : MessageCommand() {
        constructor(message: Int) : this(MyApplication.appContext.getString(message))
    }

    data class Success(
        val message: String
    ) : MessageCommand() {
        constructor(message: Int) : this(MyApplication.appContext.getString(message))
    }

    object NoInternet : MessageCommand()

    object SomethingWantWrong : MessageCommand()

    object Non : MessageCommand()


}