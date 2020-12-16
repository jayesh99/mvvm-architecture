package com.demoapp.ui

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.appcompat.app.AppCompatActivity
import com.demoapp.R
import com.demoapp.ui.home.MainActivity
import com.demoapp.utills.SharedPrefsManager
import dagger.android.AndroidInjection
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {


    val handler = Handler()

    lateinit var run: Runnable

    @Inject
    lateinit var prefsManager: SharedPrefsManager


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        run = Runnable {
            startActivity(Intent(this, MainActivity::class.java))
            finishAffinity()
        }
        setContentView(R.layout.activity_splash)
        AndroidInjection.inject(this)
    }


    override fun onResume() {
        super.onResume()
        if (::run.isInitialized)
            handler.postDelayed(run, 3000)
    }


    override fun onPause() {
        handler.removeCallbacks(run)
        super.onPause()
    }


}