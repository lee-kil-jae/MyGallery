package com.kiljae.mygallery.common

import androidx.lifecycle.MutableLiveData

class NotNullMutableLiveData<T : Any>(_defaultValue: T) : MutableLiveData<T>() {

    init {
        value = _defaultValue
    }

    override fun getValue()  = super.getValue()!!
}