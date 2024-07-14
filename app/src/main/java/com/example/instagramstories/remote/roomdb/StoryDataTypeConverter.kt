package com.example.instagramstories.remote.roomdb

import androidx.room.TypeConverter
import com.example.instagramstories.remote.model.DataModel
import com.example.instagramstories.remote.model.Storydata
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StoryDataTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStoryDataList(storyData: List<Storydata>?): String? {
        return gson.toJson(storyData)
    }

    @TypeConverter
    fun toStoryDataList(data: String?): List<Storydata>? {
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<List<Storydata>>() {}.type
        return gson.fromJson(data, listType)
    }
}
