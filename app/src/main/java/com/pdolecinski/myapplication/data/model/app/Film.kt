package com.pdolecinski.myapplication.data.model.app

data class Film(
    val title: String? = null,
    val episode_id: Long = 0,
    val opening_crawl: String? = null,
    val director: String? = null,
    val producer: String? = null,
    val release_date: String? = null,
    val characters: List<String> = emptyList()
){
    var characters_ids: List<Long> = emptyList()
}
