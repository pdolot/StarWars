package com.pdolecinski.starWars.content.page.films

import com.pdolecinski.starWars.base.BaseViewModel
import com.pdolecinski.starWars.data.database.film.FilmDao
import com.pdolecinski.starWars.data.rest.RetrofitRepository
import com.pdolecinski.starWars.di.Injector
import javax.inject.Inject

class FilmsViewModel : BaseViewModel() {

    @Inject
    lateinit var retrofitRepository: RetrofitRepository

    @Inject
    lateinit var filmDao: FilmDao

    init {
        Injector.component.inject(this)
    }

}