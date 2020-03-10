package com.pdolecinski.myapplication.data.model.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "FilmEntity")
data class Film(
    val title: String? = null,
    @PrimaryKey(autoGenerate = false)
    val episode_id: Long = 0,
    val opening_crawl: String? = null,
    val director: String? = null,
    val producer: String? = null,
    val release_date: String? = null,
    val characters: List<String> = emptyList()
) {
    var characters_ids: List<Long> = emptyList()
}
