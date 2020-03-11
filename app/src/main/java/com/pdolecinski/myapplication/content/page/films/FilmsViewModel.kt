package com.pdolecinski.myapplication.content.page.films

import com.pdolecinski.myapplication.base.BaseViewModel
import com.pdolecinski.myapplication.data.database.film.FilmDao
import com.pdolecinski.myapplication.data.rest.RetrofitRepository
import com.pdolecinski.myapplication.di.Injector
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