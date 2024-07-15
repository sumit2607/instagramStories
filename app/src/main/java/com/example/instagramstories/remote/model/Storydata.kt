package com.example.instagramstories.remote.model

import android.os.Parcel
import android.os.Parcelable

import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize
import java.io.Serializable


data class Storydata(
    @SerializedName("story_photo")
    val story_photo: String
): Serializable
