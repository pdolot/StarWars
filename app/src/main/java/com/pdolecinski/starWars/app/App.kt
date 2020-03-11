package com.pdolecinski.starWars.app

import android.app.Application
import com.pdolecinski.starWars.di.Injector

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}
