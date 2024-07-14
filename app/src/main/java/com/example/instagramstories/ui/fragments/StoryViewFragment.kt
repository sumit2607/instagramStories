package com.example.instagramstories.ui.fragments

import android.R
import android.animation.ObjectAnimator
import android.app.Dialog
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import com.example.instagramstories.databinding.FragmentStoryViewBinding
import com.example.instagramstories.remote.api.RetrofitClient
import com.example.instagramstories.remote.api.StoryApi
import com.example.instagramstories.repo.StoryRepository
import com.example.instagramstories.ui.adapter.ImagePagerAdapter
import com.example.instagramstories.viewModel.StoryViewModel
import com.example.instagramstories.viewModel.StoryViewModelFactory

class StoryViewFragment : DialogFragment() {

    private var _binding: FragmentStoryViewBinding? = null
    private val binding get() = _binding!!

    private var currentImageIndex = 0
    private val handler: Handler = Handler(Looper.getMainLooper())
    private val storyDuration = 5000L // Duration for each image

    private lateinit var storyViewModel: StoryViewModel

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // Remove the title
        return dialog
    }

    override fun onStart() {
        super.onStart()
        val window = dialog?.window
        window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        ) // Set full screen width and height
        window?.setBackgroundDrawableResource(android.R.color.white) // Optional: make background transparent
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStoryViewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initialize ViewModel
        val apiInterface = RetrofitClient.getInstance().create(StoryApi::class.java)
        val storyRepository = StoryRepository(apiInterface)
        storyViewModel = ViewModelProvider(
            this,
            StoryViewModelFactory(storyRepository)
        ).get(StoryViewModel::class.java)

        // Observe story data
        storyViewModel.storyData.observe(viewLifecycleOwner) { storyList ->
            storyList?.let {
                binding.viewPager.adapter = ImagePagerAdapter(it) { position, isForwardClick ->
                    if (isForwardClick) {
                        if (position < it.size - 1) {
                            currentImageIndex = position + 1
                        }
                    } else {
                        if (position > 0) {
                            currentImageIndex = position - 1
                        }
                    }
                    updateViewPager()
                    updateProgressBars()
                }
                setupProgressBars(it.size)
                updateViewPager()
                startAutoAdvance()
            }
        }

        // Handle clicks for left and right tap areas
        binding.leftTapArea.setOnClickListener {
            if (currentImageIndex > 0) {
                currentImageIndex--
                updateViewPager()
                updateProgressBars()
            }
        }

        binding.rightTapArea.setOnClickListener {
            if (currentImageIndex < (binding.viewPager.adapter?.itemCount ?: 0) - 1) {
                currentImageIndex++
                updateViewPager()
                updateProgressBars()
            }
        }
    }

    private fun setupProgressBars(count: Int) {
        binding.progressBarLayout.removeAllViews()
        repeat(count) {
            val progressBar =
                ProgressBar(requireContext(), null, R.attr.progressBarStyleHorizontal).apply {
                    layoutParams =
                        LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
                            .apply {
                                marginEnd = 4
                            }
                    max = storyDuration.toInt()
                    progress = 0
                }
            binding.progressBarLayout.addView(progressBar)
        }
    }

    private fun updateViewPager() {
        binding.viewPager.currentItem = currentImageIndex
    }

    private fun updateProgressBars() {
        for (i in 0 until binding.progressBarLayout.childCount) {
            val progressBar = binding.progressBarLayout.getChildAt(i) as ProgressBar
            if (i < currentImageIndex) {
                progressBar.progress = storyDuration.toInt()
            } else if (i == currentImageIndex) {
                progressBar.progress = 0
                animateProgressBar(progressBar)
            } else {
                progressBar.progress = 0
            }
        }
    }

    private fun animateProgressBar(progressBar: ProgressBar) {
        val animator = ObjectAnimator.ofInt(progressBar, "progress", 0, storyDuration.toInt())
        animator.duration = storyDuration
        animator.start()
    }

    private fun startAutoAdvance() {
        handler.postDelayed({
            if (currentImageIndex < (binding.viewPager.adapter?.itemCount ?: 0) - 1) {
                currentImageIndex++
                updateViewPager()
                updateProgressBars()
                startAutoAdvance()
            }
        }, storyDuration)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        handler.removeCallbacksAndMessages(null)
        _binding = null
    }
}



