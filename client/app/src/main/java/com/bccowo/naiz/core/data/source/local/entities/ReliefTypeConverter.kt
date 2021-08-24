package com.bccowo.naiz.core.data.source.local.entities

import androidx.room.TypeConverter
import com.google.gson.Gson

class ReliefTypeConverter {
    val gson = Gson()

    @TypeConverter
    fun fromReliefListToString(reliefs: List<Relief>): String = gson.toJson(reliefs)

    @TypeConverter
    fun fromStringToReliefList(plain: String): List<Relief> =
        gson.fromJson(plain, Array<Relief>::class.java).toList()
}