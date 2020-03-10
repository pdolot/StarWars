package com.pdolecinski.myapplication.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pdolecinski.myapplication.data.database.converter.LongConverter
import com.pdolecinski.myapplication.data.database.converter.StringConverter
import com.pdolecinski.myapplication.data.database.film.FilmDao
import com.pdolecinski.myapplication.data.database.person.PersonDao
import com.pdolecinski.myapplication.data.model.app.Film
import com.pdolecinski.myapplication.data.model.app.Person

@Database(
    entities = [Film::class, Person::class], version = 1
)
@TypeConverters(value = [LongConverter::class, StringConverter::class])
abstract class StarWarsDatabase : RoomDatabase() {
    abstract fun filmDao(): FilmDao
    abstract fun personDao(): PersonDao
}