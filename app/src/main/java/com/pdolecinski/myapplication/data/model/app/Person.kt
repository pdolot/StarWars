package com.pdolecinski.myapplication.data.model.app

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "PersonEntity")
data class Person(
    val name: String? = null,
    val height: String? = null,
    val mass: String? = null,
    val hair_color: String? = null,
    val skin_color: String? = null,
    val eye_color: String? = null,
    val birth_year: String? = null,
    val gender: String? = null,
    val url: String = ""
){
    @PrimaryKey(autoGenerate = false)
    var id: Long = 0
}
