package com.example.instagramstories.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.instagramstories.remote.roomdb.StoryDataTypeConverter
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable



@Entity(tableName = "story_table")
@TypeConverters(StoryDataTypeConverter::class)
data class DataModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @SerializedName("video_url")
    val video_url: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("user_name")
    val user_name: String,
    @SerializedName("storydata")
    val storydata: List<Storydata>? = null
) : Serializable
