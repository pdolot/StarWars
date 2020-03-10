package com.pdolecinski.myapplication.content.page.filmDetails

import com.pdolecinski.myapplication.base.BaseViewModel
import com.pdolecinski.myapplication.data.database.film.FilmDao
import com.pdolecinski.myapplication.data.database.person.PersonDao
import com.pdolecinski.myapplication.di.Injector
import javax.inject.Inject

class FilmDetailsViewModel : BaseViewModel() {

    @Inject
    lateinit var personDao: PersonDao

    @Inject
    lateinit var filmDao: FilmDao


    init {
        Injector.component.inject(this)
    }

}