package com.pdolecinski.myapplication.content.page.splash

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.pdolecinski.myapplication.base.BaseViewModel
import com.pdolecinski.myapplication.data.database.film.FilmDao
import com.pdolecinski.myapplication.data.database.person.PersonDao
import com.pdolecinski.myapplication.data.model.app.Film
import com.pdolecinski.myapplication.data.model.app.Person
import com.pdolecinski.myapplication.data.rest.RetrofitRepository
import com.pdolecinski.myapplication.di.Injector
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
    val callResult = MutableLiveData<Boolean?>()

    private fun fetchFilms(){
        rxDisposer.add(retrofitRepository.getAllFilms()
            .subscribeBy(
                onError = {
                    Log.e("FilmsViewModel", "Failed to fetch films: ${it.message}")
                    fetchPersons(1)
                },
                onSuccess = {
                    requestCounter.postValue(requestCounter.value?.plus(1))
                    insertFilmsToDatabase(it)
                    fetchPersons(1)
                }
            ))
    }


    private fun fetchPersons(page: Int){
        rxDisposer.add(retrofitRepository.fetchPeople(page)
            .subscribeBy(
                onError = {
                    Log.e("FilmsViewModel", "Failed to fetch people: ${it.message}")
                    callResult.postValue(false)
                },
                onSuccess = {
                    requestCounter.postValue(requestCounter.value?.plus(1))
                    insertPeopleToDatabase(it.results)
                    it.next?.replace("https://swapi.co/api/people/?page=","")?.toInt()?.let { p ->
                        fetchPersons(p)
                    } ?: callResult.postValue(false)
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