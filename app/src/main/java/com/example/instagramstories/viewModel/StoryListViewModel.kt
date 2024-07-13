package com.example.instagramstories.viewModel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.instagramstories.repo.StoryRepository

class StoryListViewModel(application: Application) : AndroidViewModel(application) {
    private val storyRepository = StoryRepository()
    val stories = MutableLiveData<List<Story>>()

    fun fetchStories() {
        viewModelScope.launch {
            val fetchedStories = storyRepository.getStories()
            stories.postValue(fetchedStories)
        }
    }
}

class StoryViewModel(application: Application) : AndroidViewModel(application) {
    private val storyCacheService = StoryCacheService()
    val currentStory = MutableLiveData<Story>()
    private var storyIndex = 0
    private lateinit var stories: List<Story>

    fun setStories(stories: List<Story>) {
        this.stories = stories
        storyCacheService.cacheStories(stories)
        storyIndex = 0
        currentStory.postValue(stories[storyIndex])
    }

    fun nextStory() {
        if (storyIndex < stories.size - 1) {
            storyIndex++
            currentStory.postValue(stories[storyIndex])
        }
    }

    fun previousStory() {
        if (storyIndex > 0) {
            storyIndex--
            currentStory.postValue(stories[storyIndex])
        }
    }
}
