package com.pdolecinski.myapplication.data.database.person

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pdolecinski.myapplication.data.model.app.Person

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(people: List<Person>)

    @Query("SELECT * from PersonEntity WHERE id IN (:ids)")
    fun getPeopleByIds(ids: List<Long>) : LiveData<List<Person>>
}