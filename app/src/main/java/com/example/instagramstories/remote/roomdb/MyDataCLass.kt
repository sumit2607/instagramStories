package com.example.instagramstories.remote.roomdb

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.instagramstories.remote.model.Storydata
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "story_table")
@TypeConverters(StoryDataTypeConverter::class)
data class MyDataCLass(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val video_url: String,
    val image_url: String,
    val user_name: String,
    val storydata: List<Storydata>? = null
) : Parcelable