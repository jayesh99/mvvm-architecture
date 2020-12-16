package com.demoapp.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.demoapp.R
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.main_container.*


abstract class ToolBarActivity : BaseActivity() {


    private lateinit var appBar: AppBarLayout
    private lateinit var toolBar: Toolbar

    var drawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intToolBarDrawerLayout()

    }

    private fun intToolBarDrawerLayout() {
        appBar = appBarMain
        toolBar = toolbarMain
        setSupportActionBar(toolBar)
        showHideToolBar()
        // configure for 1st time use
        drawerLayout?.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)

    }

    private fun showHideToolBar(show: Boolean = true) {

        toolBar.setBackgroundColor(ContextCompat.getColor(this, R.color.white))

        if (show) {

            appBar.visibility = View.VISIBLE
            val params = mainContainer.view?.layoutParams as CoordinatorLayout.LayoutParams
            params.behavior = AppBarLayout.ScrollingViewBehavior()
            mainContainer.view?.requestLayout()
        } else {
            appBar.visibility = View.GONE
            val params = mainContainer.view?.layoutParams as CoordinatorLayout.LayoutParams
            params.behavior = null
            mainContainer.view?.requestLayout()
        }
    }



    /**
     * manageCut = Do you want to manage cut out status bar size by your fragment layout or use default
     * return size of display status bar with cut out
     */
    fun setToolBarModeFullScreen() {
        showHideToolBar(false)

    }

    /**
     * manageCut = Do you want to manage cut out status bar size by your fragment layout or use default
     * return size of display status bar with cut out
     */
    fun setToolBarModeMain(title: String) {
        showHideToolBar(true)
        textViewTitle.text = title
        toolBar.navigationIcon = null
        toolBar.setNavigationOnClickListener {
        }

    }



    fun setToolBarModeBack(text: String, icon: Int) {
        showHideToolBar(true)
        toolBar.setNavigationIcon(icon)
        toolBar.setNavigationOnClickListener {
            onBackPressed()
        }
        textViewTitle.text = text

    }



}