package com.example.instagramstories.ui.fragments

import androidx.fragment.app.Fragment


/**
 * A simple [Fragment] subclass.
 * Use the [StoryViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//class StoryViewFragment : Fragment() {
//    private lateinit var viewModel: StoryViewModel
//    private lateinit var handler: Handler
//    private lateinit var runnable: Runnable
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_story_view, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(StoryViewModel::class.java)
//        val stories = StoryViewFragmentArgs.fromBundle(requireArguments()).stories
//        viewModel.setStories(stories)
//
//        viewModel.currentStory.observe(viewLifecycleOwner) { story ->
//            Glide.with(this).load(story.imageUrl).into(view.findViewById(R.id.storyImageView))
//            scheduleNextStory(story.duration)
//        }
//
//        val storyContainer = view.findViewById<View>(R.id.storyContainer)
//        storyContainer.setOnTouchListener(object : OnSwipeTouchListener(context) {
//            override fun onSwipeLeft() {
//                viewModel.nextStory()
//            }
//
//            override fun onSwipeRight() {
//                viewModel.previousStory()
//            }
//        })
//    }
//
//    private fun scheduleNextStory(duration: Long) {
//        handler = Handler(Looper.getMainLooper())
//        runnable = Runnable { viewModel.nextStory() }
//        handler.postDelayed(runnable, duration)
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        handler.removeCallbacks(runnable)
//    }
//}
