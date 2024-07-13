package com.example.instagramstories.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.instagramstories.repo.StoryRepository

class VideoViewModelFactory(private val videoRepo: StoryRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return VideoViewModel(videoRepo) as T
    }

}