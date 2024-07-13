package com.example.instagramstories.remote.model

data class Story(
    val id: String,
    val imageUrl: String,
    val duration: Long // in milliseconds
)
