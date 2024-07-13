package com.example.instagramstories.ui.adapter

class StoryAdapter(private val onClick: (Story) -> Unit) :
    ListAdapter<Story, StoryAdapter.StoryViewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_story, parent, false)
        return StoryViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class StoryViewHolder(itemView: View, val onClick: (Story) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.storyImageView)
        private var currentStory: Story? = null

        init {
            itemView.setOnClickListener {
                currentStory?.let { onClick(it) }
            }
        }

        fun bind(story: Story) {
            currentStory = story
            Glide.with(itemView.context)
                .load(story.imageUrl)
                .into(imageView)
        }
    }

    class DiffCallback : DiffUtil.ItemCallback<Story>() {
        override fun areItemsTheSame(oldItem: Story, newItem: Story) = oldItem.id == newItem.id
        override fun areContentsTheSame(oldItem: Story, newItem: Story) = oldItem == newItem
    }
}
