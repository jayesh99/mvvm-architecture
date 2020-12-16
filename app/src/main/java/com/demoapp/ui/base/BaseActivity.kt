package com.demoapp.ui.base

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.demoapp.rx.RxHandler
import com.demoapp.utills.Logger
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BaseActivity : AppCompatActivity(), RxHandler {


    val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // DaggerActivityComponent
        setContentView(getMainLayout())
    }

    abstract fun getMainLayout(): Int


    override fun onError(e: Throwable) {
        Toast.makeText(this, " ${e?.message}", Toast.LENGTH_LONG).show()
        Logger.log(">>>>>>> "+ "  ${e?.message}")
    }


    override fun onSubscribe(d: Disposable) {
        compositeDisposable.add(d)
    }

    override fun onDestroy() {
        if (!compositeDisposable.isDisposed)
            compositeDisposable.dispose()
        super.onDestroy()
    }

}