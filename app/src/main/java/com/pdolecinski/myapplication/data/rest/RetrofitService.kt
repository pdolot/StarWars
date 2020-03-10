package com.pdolecinski.myapplication.data.rest

import com.pdolecinski.myapplication.data.model.api.FilmsResponse
import com.pdolecinski.myapplication.data.model.api.PeopleResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("films")
    fun fetchAllFilms(): Single<FilmsResponse>

    @GET("people")
    fun fetchPeople(@Query("page") page: Int): Single<PeopleResponse>
}
