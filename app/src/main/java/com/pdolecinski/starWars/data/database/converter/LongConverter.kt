package com.pdolecinski.starWars.data.database.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class LongConverter {

    @TypeConverter
    fun fromString(value: String): List<Long> {
        val listType = object : TypeToken<List<Long>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromList(list: List<Long>): String {
        return Gson().toJson(list)
    }
}