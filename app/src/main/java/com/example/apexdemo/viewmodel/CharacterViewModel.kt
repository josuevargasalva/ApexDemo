package com.example.apexdemo.viewmodel

import com.example.apexdemo.common.BaseListViewModel
import com.example.apexdemo.common.State
import com.example.apexdemo.models.MCharacter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CharacterViewModel : BaseListViewModel<MCharacter>() {

    //Consumo del request utilizando RX
    override fun getData() {
        state.value = State.LOADING
        disposeBag.add(provider.getCharacters()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { }
            .subscribe({
                currentData.value = it.results
                state.value = State.IDLE
                state.value = State.SUCCESS
            }, { throwable ->
                state.value = State.IDLE
                state.value = State.ERROR
            }
            ))
    }
}