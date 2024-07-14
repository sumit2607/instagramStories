package com.example.instagramstories.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.instagramstories.databinding.ItemImageBinding
import com.example.instagramstories.remote.model.DataModel


class ImagePagerAdapter(
    private val images: List<DataModel>,
    private val onImageClick: (Int, Boolean) -> Unit // Lambda to handle image clicks with direction
) : RecyclerView.Adapter<ImagePagerAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(val binding: ItemImageBinding) :
        RecyclerView.ViewHolder(binding.root) {
        init {
            binding.imageView.setOnClickListener { view ->
                // Determine click position
                val isForwardClick = view.x > view.width / 2
                val position = adapterPosition
                onImageClick(
                    position,
                    isForwardClick
                ) // Invoke the click callback with the current position
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val binding = ItemImageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val dataModel = images[position]
        Glide.with(holder.binding.root.context)
            .load(dataModel.image_url)
            .into(holder.binding.imageView)

        // Set the layout parameters to match parent dimensions
        holder.binding.root.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun getItemCount(): Int = images.size
}
