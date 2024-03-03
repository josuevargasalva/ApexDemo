package com.example.apexdemo.common

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.apexdemo.api.Api
import com.example.apexdemo.api.ApiProvider
import io.reactivex.disposables.CompositeDisposable

//modelo base para ViewModel
open class BaseViewModel (
    var provider: Api = ApiProvider.provider()
) : ViewModel() {
    val disposeBag = CompositeDisposable()
    val state = MutableLiveData<State>()
}