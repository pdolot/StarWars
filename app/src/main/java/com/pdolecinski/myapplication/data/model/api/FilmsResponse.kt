package com.pdolecinski.myapplication.data.model.api

import com.pdolecinski.myapplication.data.model.app.Film

data class FilmsResponse(
    val count: Int = 0,
    val results: List<Film> = emptyList()
)
