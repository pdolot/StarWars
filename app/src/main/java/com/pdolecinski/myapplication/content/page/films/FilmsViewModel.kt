package com.pdolecinski.myapplication.content.page.films

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.pdolecinski.myapplication.base.BaseViewModel
import com.pdolecinski.myapplication.data.database.film.FilmDao
import com.pdolecinski.myapplication.data.model.app.Film
import com.pdolecinski.myapplication.data.rest.RetrofitRepository
import com.pdolecinski.myapplication.di.Injector
import io.reactivex.rxkotlin.subscribeBy
import javax.inject.Inject

class FilmsViewModel : BaseViewModel() {

    @Inject
    lateinit var retrofitRepository: RetrofitRepository

    @Inject
    lateinit var filmDao: FilmDao

    init {
        Injector.component.inject(this)
    }

    val films = MutableLiveData<List<Film>>()

    private fun getFilms(){
        rxDisposer.add(retrofitRepository.getAllFilms()
            .subscribeBy(
                onError = {
                    Log.e("FilmsViewModel", "Failed to fetch films: ${it.message}")
                },
                onSuccess = {
                    films.postValue(it)
                }
            ))
    }

}