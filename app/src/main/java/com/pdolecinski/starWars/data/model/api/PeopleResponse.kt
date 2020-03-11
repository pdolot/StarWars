package com.pdolecinski.starWars.data.model.api

import com.pdolecinski.starWars.data.model.app.Person

data class PeopleResponse(
    val count: Int = 0,
    val next: String? = null,
    val previous: String? = null,
    val results: List<Person> = emptyList()
)
