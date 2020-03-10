package com.pdolecinski.myapplication.di

import com.pdolecinski.myapplication.content.page.filmDetails.FilmDetailsViewModel
import com.pdolecinski.myapplication.content.page.films.FilmsViewModel
import com.pdolecinski.myapplication.content.page.splash.SplashViewModel
import com.pdolecinski.myapplication.di.module.DbModule
import com.pdolecinski.myapplication.di.module.RestModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RestModule::class,
        DbModule::class
    ]
)
interface AppComponent {
    fun inject(into: FilmsViewModel)
    fun inject(into: SplashViewModel)
    fun inject(into: FilmDetailsViewModel)
}