package com.example.apexdemo.common

import androidx.lifecycle.MutableLiveData

//Modelo base para consumo de listas
open class BaseListViewModel<T> : BaseViewModel(), ViewModelList<T> {

    val currentData = MutableLiveData<List<T>>()

    override fun getData() { }

    override fun objectAt(index: Int): T {
        return currentData.value!![index]
    }
}