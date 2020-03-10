package com.pdolecinski.myapplication.di

import com.pdolecinski.myapplication.di.module.RestModule

object Injector {
    lateinit var component: AppComponent

    fun init(){
        component =  DaggerAppComponent.builder()
            .restModule(RestModule())
            .build()
    }
}