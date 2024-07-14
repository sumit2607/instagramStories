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
import com.example.instagramstories.repo.StoryRepository
import com.example.instagramstories.ui.adapter.StoryAdapter
import com.example.instagramstories.viewModel.StoryViewModel
import com.example.instagramstories.viewModel.StoryViewModelFactory


class StoryListFragment() : Fragment() {
    private lateinit var storyViewModel: StoryViewModel
    private var _binding: FragmentStoryListBinding? = null

    private lateinit var storyAdapter: StoryAdapter
    private lateinit var dataModel: DataModel
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStoryListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        val apiInterface = RetrofitClient.getInstance().create(StoryApi::class.java)

        val storyRepository = StoryRepository(apiInterface)

        storyViewModel =
            ViewModelProvider(
                this,
                StoryViewModelFactory(storyRepository)
            )[StoryViewModel::class.java]
        storyViewModel.storyData.observe(viewLifecycleOwner) {
            Log.d("TAG", "onCreate: ${it[0].video_url}")
            Log.d("TAG", "onCreatdssde: ${it[0].storydata?.get(0)}")

            storyAdapter = StoryAdapter(it as List<DataModel>) { pos ->
                Log.d("TAG", "setupRecyclerview: " + "$pos")
                // Example of setting story list in ViewModel
                val storyList = it[pos].storydata
                if (storyList != null) {
                    Log.d("TAG", "setupRecyclerview: " + "$storyList")
                    sharedViewModel.setStoryList(storyList) // Set story list to SharedViewModel
                }
                val storyViewFragment = StoryViewFragment()
                fragmentManager?.let { it1 -> storyViewFragment.show(it1, "frome") }

            }
            binding.pulseRecyclerview.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.pulseRecyclerview.adapter = storyAdapter

        }
    }
}