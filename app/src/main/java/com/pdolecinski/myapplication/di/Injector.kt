package com.pdolecinski.myapplication.di

import com.pdolecinski.myapplication.app.App
import com.pdolecinski.myapplication.di.module.DbModule
import com.pdolecinski.myapplication.di.module.RestModule

object Injector {
    lateinit var component: AppComponent

    fun init(application: App){
        component =  DaggerAppComponent.builder()
            .restModule(RestModule())
            .dbModule(DbModule(application))
            .build()
    }
}