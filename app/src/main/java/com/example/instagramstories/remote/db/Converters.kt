package com.example.instagramstories.remote.roomdb.db

import androidx.room.TypeConverter
import com.example.instagramstories.remote.model.Storydata
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromResultModelList(resultModels: List<Storydata>?): String? {
        return gson.toJson(resultModels)
    }

    @TypeConverter
    fun toResultModelList(resultModelsString: String?): List<Storydata>? {
        val type = object : TypeToken<List<Storydata>>() {}.type
        return gson.fromJson(resultModelsString, type)
    }
}
