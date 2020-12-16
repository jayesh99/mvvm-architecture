package com.demoapp.ui.base

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.demoapp.R
import com.maan.mandir.utills.MessageCommand
import com.demoapp.data.api.ServerException
import com.demoapp.data.db.AppDataBase
import com.demoapp.utills.NavigationCommand
import com.demoapp.rx.RxBus
import com.demoapp.rx.RxHandler
import com.demoapp.rx.observer.SingleWithOutHandler
import com.demoapp.ui.home.MainActivity
import com.demoapp.utills.SharedPrefsManager
import com.demoapp.utills.SingleLiveEvent
import com.demoapp.utills.makeThreadSafe
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import java.io.IOException
import javax.inject.Inject

open class BaseViewModel : ViewModel(), RxHandler {



    @Inject
    lateinit var application: Application

    @Inject
    lateinit var appDataBase: AppDataBase

    @Inject
    lateinit var prefsManager: SharedPrefsManager

    @Inject
    lateinit var rxBus: RxBus

    val compositeDisposable = CompositeDisposable()

    val messageLiveData = SingleLiveEvent<MessageCommand>()


    val navigation = SingleLiveEvent<NavigationCommand>()


    override fun onCleared() {
        super.onCleared()

        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
    }


    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }


    override fun onError(e: Throwable) {
        when (e) {
            is IOException -> messageLiveData.postValue(MessageCommand.NoInternet)
            is ServerException ->{
               if (e.type == ServerException.Companion.Type.AUTH)
               {
                   appDataBase.clearAllTables()
                   prefsManager.clearPrefs()

               }
                else e.message?.let {
                   messageLiveData.postValue(MessageCommand.Error(it))
               }
            }

            else -> messageLiveData.postValue(MessageCommand.SomethingWantWrong)

        }
    }



    fun getServerErrorMessage(e: Throwable): String {

        if(e is ServerException &&e.type == ServerException.Companion.Type.AUTH)
        {
            Single.create<String> {
                appDataBase.clearAllTables()
            }.makeThreadSafe()
                .subscribe(SingleWithOutHandler())

            prefsManager.clearPrefs()
            navigation.postValue(NavigationCommand.ToActivity(MainActivity::class.java))
        }
        when (e) {
            is IOException -> return application.getString(R.string.no_internet)
            is ServerException -> return e.message!!
            else -> return application.getString(R.string.error_comman)
        }
    }


    fun onClickBack() {
        navigation.postValue(NavigationCommand.Back)
    }

}