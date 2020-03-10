package com.pdolecinski.myapplication.app

import android.app.Application
import com.pdolecinski.myapplication.di.Injector

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}
