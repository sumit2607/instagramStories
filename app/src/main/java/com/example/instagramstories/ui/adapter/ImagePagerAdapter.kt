package com.example.instagramstories.ui.adapter


import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramstories.databinding.ItemImageBinding
import com.example.instagramstories.remote.model.Storydata
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer

class ImagePagerAdapter(
    private val images: List<Storydata>,
    private val onImageClick: (Int, Boolean, Boolean) -> Unit
) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    private var currentPlayer: SimpleExoPlayer? = null

    inner class ImageViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {

        var player: SimpleExoPlayer? = null
        private var mediaItem: MediaItem? = null

        init {
            binding.apply {
                leftTapArea.setOnClickListener {
                    val position = adapterPosition
                    releasePlayer() // Release any existing player instance
                    onImageClick(position, false, true)
                }

                rightTapArea.setOnClickListener {
                    val position = adapterPosition
                    releasePlayer() // Release any existing player instance
                    onImageClick(position, true, false)
                }
            }
        }

        fun bind(dataModel: Storydata) {
            releasePlayer() // Release any existing player instance

            val videoUri = Uri.parse(dataModel.story_photo)
            val context = binding.root.context

            // Create a SimpleExoPlayer instance
            player = SimpleExoPlayer.Builder(context).build()

            // Create a MediaItem
            mediaItem = MediaItem.fromUri(videoUri)

            // Set media item to player
            player?.setMediaItem(mediaItem!!)

            // Set player to player view
            binding.playerView.player = player

            // Prepare the player asynchronously
            player?.prepare()
            player?.playWhenReady = true

            currentPlayer = player
        }

        fun releasePlayer() {
            player?.release()
            player = null
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val dataModel = images[position]
        holder.bind(dataModel)
        holder.binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun getItemCount(): Int = images.size

    override fun onViewRecycled(holder: ImageViewHolder) {
        super.onViewRecycled(holder)
        holder.releasePlayer() // Ensure player resources are released when recycled
        if (holder.player == currentPlayer) {
            currentPlayer = null
        }
    }

    // Release the current player when the view pager position changes
    fun releaseCurrentPlayer() {
        currentPlayer?.pause()
        currentPlayer?.release()
        currentPlayer = null
    }
}
