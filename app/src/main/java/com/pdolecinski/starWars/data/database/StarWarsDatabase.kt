package com.pdolecinski.starWars.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pdolecinski.starWars.data.database.converter.LongConverter
import com.pdolecinski.starWars.data.database.converter.StringConverter
import com.pdolecinski.starWars.data.database.film.FilmDao
import com.pdolecinski.starWars.data.database.person.PersonDao
import com.pdolecinski.starWars.data.model.app.Film
import com.pdolecinski.starWars.data.model.app.Person

@Database(
    entities = [Film::class, Person::class], version = 1
)
@TypeConverters(value = [LongConverter::class, StringConverter::class])
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun personDao(): PersonDao
}