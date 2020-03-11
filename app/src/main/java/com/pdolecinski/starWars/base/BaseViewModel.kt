package com.pdolecinski.starWars.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    var rxDisposer = CompositeDisposable()

}
