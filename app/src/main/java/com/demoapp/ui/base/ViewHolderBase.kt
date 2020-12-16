package com.sujanix.ui.base

import android.view.View
import androidx.recyclerview.widget.RecyclerView

abstract class ViewHolderBase  <T>  (view:View)  : RecyclerView.ViewHolder(view) {
    abstract fun bind(position:Int , data : T )
}