package com.pdolecinski.myapplication.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {
    var rxDisposer = CompositeDisposable()

    override fun onCleared() {
        super.onCleared()
        rxDisposer.dispose()
    }
}
