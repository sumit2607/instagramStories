package com.example.instagramstories.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.instagramstories.remote.model.DataModel
import com.example.instagramstories.repo.StoryRepository
import kotlinx.coroutines.launch

class StoryViewModel(private val repository: StoryRepository) : ViewModel() {

    val storyData: List<DataModel>? = repository.allStories

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> get() = _loading

    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    // Fetch data from API and save to database
    fun fetchStories() {
        _loading.value = true
        viewModelScope.launch {
            try {
                repository.fetchAndSaveStories()
            } catch (e: Exception) {
                _error.value = e.message
            } finally {
                _loading.value = false
            }
        }
    }

//    // Insert a single story
//    fun insert(story: DataModel) {
//        viewModelScope.launch {
//            repository.insert(story)
//        }
//    }

    // Delete all stories
//    fun deleteAll() {
//        viewModelScope.launch {
//            repository.deleteAll()
//        }
//    }
}


