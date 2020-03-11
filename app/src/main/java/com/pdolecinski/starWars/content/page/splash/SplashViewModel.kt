package com.pdolecinski.starWars.content.page.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pdolecinski.starWars.base.BaseViewModel
import com.pdolecinski.starWars.data.database.film.FilmDao
import com.pdolecinski.starWars.data.database.person.PersonDao
import com.pdolecinski.starWars.data.model.app.Film
import com.pdolecinski.starWars.data.model.app.Person
import com.pdolecinski.starWars.data.rest.RetrofitRepository
import com.pdolecinski.starWars.di.Injector
import io.reactivex.rxkotlin.subscribeBy
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashViewModel : BaseViewModel() {

    @Inject
    lateinit var retrofitRepository: RetrofitRepository

    @Inject
    lateinit var filmDao: FilmDao

    @Inject
    lateinit var personDao: PersonDao

    init {
        Injector.component.inject(this)
        fetchFilms()
    }

    var requestCounter = MutableLiveData(0)

    val films = MutableLiveData<List<Film>>()
    val isInProgress = MutableLiveData<Boolean?>()

    private fun fetchFilms() {
        rxDisposer.add(retrofitRepository.getAllFilms()
            .subscribeBy(
                onError = {
                    requestCounter.postValue(requestCounter.value?.plus(1))
                    Log.e("SplashViewModel", "Failed to fetch films: ${it.message}")
                    fetchPersons(1)
                },
                onSuccess = {
                    requestCounter.postValue(requestCounter.value?.plus(1))
                    insertFilmsToDatabase(it)
                    fetchPersons(1)
                }
            ))
    }


    private fun fetchPersons(page: Int) {
        rxDisposer.add(retrofitRepository.fetchPeople(page)
            .subscribeBy(
                onError = {
                    requestCounter.postValue(
                        requestCounter.value?.plus(
                            10 - (requestCounter.value ?: 0)
                        )
                    )
                    Log.e("SplashViewModel", "Failed to fetch people: ${it.message}")
                    isInProgress.postValue(false)
                },
                onSuccess = {
                    requestCounter.postValue(requestCounter.value?.plus(1))
                    insertPeopleToDatabase(it.results)
                    it.next?.replace(
                        "https://swapi.co/api/people/?page=",
                        ""
                    )?.toInt()?.let { p ->
                        fetchPersons(p)
                    } ?: isInProgress.postValue(false)
                }
            ))
    }

    private fun insertPeopleToDatabase(people: List<Person>) = viewModelScope.launch {
        personDao.insert(people)
    }

    private fun insertFilmsToDatabase(films: List<Film>) = viewModelScope.launch {
        filmDao.insert(films)
    }
}