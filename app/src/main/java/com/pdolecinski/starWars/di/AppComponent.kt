package com.pdolecinski.starWars.di

import com.pdolecinski.starWars.content.page.filmDetails.FilmDetailsViewModel
import com.pdolecinski.starWars.content.page.films.FilmsViewModel
import com.pdolecinski.starWars.content.page.splash.SplashViewModel
import com.pdolecinski.starWars.di.module.DbModule
import com.pdolecinski.starWars.di.module.RestModule
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