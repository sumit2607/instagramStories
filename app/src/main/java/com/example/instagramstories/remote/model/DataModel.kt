package com.example.instagramstories.remote.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName



@Entity(tableName = "app_database")
data class DataModel(
    @PrimaryKey(autoGenerate = true) val id: Long = 0L,
    @SerializedName("video_url")
    val video_url: String,
    @SerializedName("image_url")
    val image_url: String,
    @SerializedName("user_name")
    val user_name: String,
    @SerializedName("results") val resultModels: List<Storydata>? = null
)
