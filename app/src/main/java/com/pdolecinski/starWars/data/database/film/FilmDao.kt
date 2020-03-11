package com.pdolecinski.starWars.data.database.film

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdolecinski.starWars.data.model.app.Film

@Dao
interface FilmDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(film: List<Film>)

    @Query("SELECT * FROM FilmEntity")
    fun getAll(): LiveData<List<Film>>

    @Query("SELECT * FROM FilmEntity WHERE episode_id =:id")
    fun getFilmById(id: Long): LiveData<Film?>
}