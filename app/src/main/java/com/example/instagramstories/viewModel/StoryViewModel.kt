package com.example.instagramstories.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramstories.remote.model.DataModel
import com.example.instagramstories.repo.StoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class StoryViewModel(private val videoRepo: StoryRepository) : ViewModel() {

    init {
        viewModelScope.launch(Dispatchers.IO) {
            videoRepo.getMemes()
        }
    }

    val storyData: LiveData<List<DataModel>>
        get() = videoRepo.video

}