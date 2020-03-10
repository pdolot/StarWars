package com.pdolecinski.myapplication.di.module

import androidx.room.Room
import com.pdolecinski.myapplication.app.App
import com.pdolecinski.myapplication.data.database.StarWarsDatabase
import com.pdolecinski.myapplication.data.database.film.FilmDao
import com.pdolecinski.myapplication.data.database.person.PersonDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DbModule(private val application: App) {
    @Singleton
    @Provides
    fun provideRoomDatabase(): StarWarsDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            StarWarsDatabase::class.java,
            "StarWarsDatabase"
        ).build()
    }

    @Singleton
    @Provides
    fun provideFilmDao(database: StarWarsDatabase): FilmDao{
        return database.filmDao()
    }

    @Singleton
    @Provides
    fun providePersonDao(database: StarWarsDatabase): PersonDao{
        return database.personDao()
    }
}