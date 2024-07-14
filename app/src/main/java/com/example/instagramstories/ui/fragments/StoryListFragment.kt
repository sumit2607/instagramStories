package com.example.instagramstories.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramstories.databinding.FragmentStoryListBinding
import com.example.instagramstories.remote.api.RetrofitClient
import com.example.instagramstories.remote.api.StoryApi
import com.example.instagramstories.remote.model.DataModel
import com.example.instagramstories.remote.model.SharedViewModel
import com.example.instagramstories.remote.roomdb.StoryDatabase
import com.example.instagramstories.repo.StoryRepository
import com.example.instagramstories.ui.adapter.StoryAdapter
import com.example.instagramstories.viewModel.StoryViewModel
import com.example.instagramstories.viewModel.StoryViewModelFactory


class StoryListFragment : Fragment() {

    private lateinit var storyViewModel: StoryViewModel
    private lateinit var storyAdapter: StoryAdapter
    private lateinit var binding: FragmentStoryListBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val application = requireNotNull(this.activity).application
        val storyDao = StoryDatabase.getDatabase(application).storyDao()
        val storyApiService = RetrofitClient.getInstance().create(StoryApi::class.java)
        val repository = StoryRepository(storyDao, storyApiService)
        storyViewModel = ViewModelProvider(this, StoryViewModelFactory(repository)).get(StoryViewModel::class.java)

        storyViewModel.storyData.observe(viewLifecycleOwner) { storyList ->
            Log.d("TAG", "onCreate: ${storyList[0].video_url}")
            Log.d("TAG", "onCreate: ${storyList[0].storydata?.get(0)}")

            storyAdapter = StoryAdapter(storyList as List<DataModel>) { pos ->
                Log.d("TAG", "setupRecyclerview: $pos")
                val storyData = storyList[pos].storydata
                if (storyData != null) {
                    Log.d("TAG", "setupRecyclerview: $storyData")
                    sharedViewModel.setStoryList(storyData) // Set story list to SharedViewModel
                }
                val storyViewFragment = StoryViewFragment()
                fragmentManager?.let { storyViewFragment.show(it, "frome") }
            }

            binding.pulseRecyclerview.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.pulseRecyclerview.adapter = storyAdapter
        }

        storyViewModel.loading.observe(viewLifecycleOwner) { isLoading ->
            // Show or hide loading indicator
        }

        storyViewModel.error.observe(viewLifecycleOwner) { errorMessage ->
            // Show error message
        }

        // Fetch stories from API when fragment is created
        storyViewModel.fetchStories()
    }
}

