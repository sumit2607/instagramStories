package com.example.instagramstories.remote.model

import android.os.Parcel
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Storydata(
    val story_photo: String
):Parcelable
