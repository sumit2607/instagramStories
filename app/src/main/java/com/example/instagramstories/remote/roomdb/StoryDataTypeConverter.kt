package com.example.instagramstories.remote.roomdb

import androidx.room.TypeConverter
import com.example.instagramstories.remote.model.DataModel
import com.example.instagramstories.remote.model.Storydata
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StoryDataTypeConverter {
    @TypeConverter
    fun fromStorydataList(value: List<Storydata>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<Storydata>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toStorydataList(value: String?): List<Storydata>? {
        val gson = Gson()
        val type = object : TypeToken<List<Storydata>>() {}.type
        return gson.fromJson(value, type)
    }
}
