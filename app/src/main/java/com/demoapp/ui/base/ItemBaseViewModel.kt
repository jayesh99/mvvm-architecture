package com.sujanix.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ItemBaseViewModel(val  errorMessage : MutableLiveData<String>) : ViewModel()