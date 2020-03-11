package com.pdolecinski.starWars.data.rest

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class RetrofitRepository(private val retrofitService: RetrofitService) {

    fun getAllFilms() =
        retrofitService.fetchAllFilms()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.results.forEach { f ->
                    val charactersIds = ArrayList<Long>()
                    f.characters.forEach{c ->
                        charactersIds.add( c.replace("https://swapi.co/api/people/","").replace("/","").toLong())
                    }
                    f.characters_ids = charactersIds
                }
                it.results
            }

    fun fetchPeople(page: Int) =
        retrofitService.fetchPeople(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {
                it.results.forEach {p ->
                    p.id = p.url.replace("https://swapi.co/api/people/","").replace("/","").toLong()
                }
                it
            }
}
