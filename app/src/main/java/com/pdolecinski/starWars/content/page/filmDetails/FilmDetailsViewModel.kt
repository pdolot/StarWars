package com.pdolecinski.starWars.content.page.filmDetails

import com.pdolecinski.starWars.base.BaseViewModel
import com.pdolecinski.starWars.data.database.film.FilmDao
import com.pdolecinski.starWars.data.database.person.PersonDao
import com.pdolecinski.starWars.di.Injector
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