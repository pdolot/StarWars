package com.pdolecinski.starWars.di

import com.pdolecinski.starWars.app.App
import com.pdolecinski.starWars.di.module.DbModule
import com.pdolecinski.starWars.di.module.RestModule

object Injector {
    lateinit var component: AppComponent

    fun init(application: App){
        component =  DaggerAppComponent.builder()
            .restModule(RestModule())
            .dbModule(DbModule(application))
            .build()
    }
}