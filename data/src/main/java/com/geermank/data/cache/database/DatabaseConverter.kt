package com.geermank.data.cache.database

import androidx.room.TypeConverter

object DatabaseConverter {

    @JvmStatic
    @TypeConverter
    fun stringToList(value: String): List<String> {
        return value.split(",")
    }

    @JvmStatic
    @TypeConverter
    fun listToString(values: List<String>): String {
        return values.joinToString(",")
    }
}
