package com.demoapp.utills

import android.util.Log
import com.demoapp.BuildConfig

class Logger  {

    companion object{

        fun log(text:String){

            if (BuildConfig.DEBUG){
                if (text.length>3000){
                    Log.e(getLogTagWithMethod(),""+text.substring(0,3000))
                    log(text.substring(3000))
                }else
                    Log.e(getLogTagWithMethod(),""+text)

            }

        }

        private fun getLogTagWithMethod(): String {

                val stack = Throwable().fillInStackTrace()
                val trace = stack.stackTrace
                return trace[2].className + "." + trace[2].methodName + ":" + trace[2].lineNumber

        }

    }

}