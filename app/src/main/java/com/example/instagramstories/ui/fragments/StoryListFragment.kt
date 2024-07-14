package com.example.instagramstories.ui.fragments

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramstories.databinding.FragmentStoryListBinding
import com.example.instagramstories.remote.api.RetrofitClient
import com.example.instagramstories.remote.api.StoryApi
import com.example.instagramstories.repo.StoryRepository
import com.example.instagramstories.ui.adapter.PulseAdapter
import com.example.instagramstories.viewModel.VideoViewModel
import com.example.instagramstories.viewModel.VideoViewModelFactory


class StoryListFragment() : Fragment() {
    private lateinit var videoViewModel: VideoViewModel
    private var _binding: FragmentStoryListBinding? = null

    private lateinit var pulseAdapter: PulseAdapter

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

//        val apiinterface = RetrofitClient.getInstance().create(StoryApi::class.java)
//
//        val videoRepo = StoryRepository(apiinterface)
//
//        videoViewModel =
//            ViewModelProvider(this, VideoViewModelFactory(videoRepo))[VideoViewModel::class.java]
//        videoViewModel.video.observe(viewLifecycleOwner) {
//            Log.d("TAG", "onCreate: ${it.get(0).video_url}")
//           // adapter = VideoAdapter(it)
//            val snapHelper = PagerSnapHelper()
//            snapHelper.attachToRecyclerView(binding.recyclerView)
//            binding.recyclerView.layoutManager =
//                LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//            //binding.recyclerView.adapter = adapter
////
//        }

    }

    private fun setupRecyclerview() {
        val apiinterface = RetrofitClient.getInstance().create(StoryApi::class.java)

        val videoRepo = StoryRepository(apiinterface)

        videoViewModel =
            ViewModelProvider(this, VideoViewModelFactory(videoRepo))[VideoViewModel::class.java]
        videoViewModel.video.observe(viewLifecycleOwner) {
            Log.d("TAG", "onCreate: ${it.get(0).video_url}")
            pulseAdapter = PulseAdapter(it)
            binding.pulseRecyclerview.layoutManager =
                LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
            binding.pulseRecyclerview.adapter = pulseAdapter

        }
    }
}