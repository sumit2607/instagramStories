package com.example.instagramstories.remote.roomdb

import androidx.room.TypeConverter
import com.example.instagramstories.remote.model.DataModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class StoryDataTypeConverter {
    private val gson = Gson()

    @TypeConverter
    fun fromStoryDataList(storyData: List<DataModel>?): String? {
        return gson.toJson(storyData)
    }

    @TypeConverter
    fun toStoryDataList(data: String?): List<DataModel>? {
        if (data == null) {
            return null
        }
        val listType = object : TypeToken<List<DataModel>>() {}.type
        return gson.fromJson(data, listType)
    }
}
