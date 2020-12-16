package com.sujanix.ui.base


import androidx.lifecycle.MutableLiveData
import com.demoapp.databinding.ItemViewProgressBinding

class ViewHolderProgress<T>(val itemViewProgressBinding: ItemViewProgressBinding, val errorMessage : MutableLiveData<String>) :
    ViewHolderBase<T>(itemViewProgressBinding.root) {

    override fun bind(position: Int, data: T) {
        itemViewProgressBinding.baseViewModel = ItemBaseViewModel(errorMessage)
    }


    companion object{
        const val PROGRESS_VIEW_TYPE =0
    }

}