package com.demoapp.ui.base

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.demoapp.R
import com.demoapp.ui.home.MainActivity
import com.demoapp.utills.NavigationCommand


abstract class BaseFragment : Fragment() {

    abstract fun getBaseViewModel(): BaseViewModel


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getBaseViewModel().navigation.observe(this, Observer {
            when (it) {
                is NavigationCommand.To ->
                    navigator.navigate(it.directions)
                is NavigationCommand.ToActivity -> {
                    startActivity(Intent(activity, it.type))
                    activity?.finishAffinity()
                }
                is NavigationCommand.Back ->
                    activity?.onBackPressed()
            }
        })

        Intent(activity, MainActivity::class.java)

    }



    val navigator by lazy { findNavController() }



    fun setToolBarModeFullScreen() {
         if (activity is ToolBarActivity)
            (activity as ToolBarActivity).setToolBarModeFullScreen()
    }



    fun setToolBarModeMain(title: String) {
         if (activity is ToolBarActivity)
            (activity as ToolBarActivity).setToolBarModeMain(title)
    }


    fun setToolBarModeBack(
        tital: String,
        icon: Int = R.drawable.ic_back_arrow
    ) {
        if (activity is ToolBarActivity)
            (activity as ToolBarActivity).setToolBarModeBack(tital, icon)

    }




}