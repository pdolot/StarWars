package com.pdolecinski.myapplication.di

import com.pdolecinski.myapplication.di.module.RestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RestModule::class
    ]
)
interface AppComponent {
}