package com.example.instagramstories.ui.fragments

import androidx.fragment.app.Fragment

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StoryListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
//class StoryListFragment : Fragment() {
//    private lateinit var viewModel: VideoViewModel
//    private lateinit var adapter: PulseAdapter
//    private lateinit var binding: FragmentStoryListBinding
//
//    override fun onCreateView(
//        inflater: LayoutInflater, container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        return inflater.inflate(R.layout.fragment_story_list, container, false)
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(VideoViewModel::class.java)
////        adapter = StoryAdapter { story ->
////            val action = StoryListFragmentDirections.actionStoryListFragmentToStoryViewFragment(story)
////            findNavController().navigate(action)
////        }
//
//        val recyclerView = view.findViewById<RecyclerView>(R.id.storyRecyclerView)
//        recyclerView.adapter = adapter
//        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
//
//        viewModel.stories.observe(viewLifecycleOwner) { stories ->
//            adapter.submitList(stories)
//        }
//
//        viewModel.fetchStories()
//    }
//}
