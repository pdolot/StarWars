package com.pdolecinski.starWars.data.model.api

import com.pdolecinski.starWars.data.model.app.Film

data class FilmsResponse(
    val count: Int = 0,
    val results: List<Film> = emptyList()
)
