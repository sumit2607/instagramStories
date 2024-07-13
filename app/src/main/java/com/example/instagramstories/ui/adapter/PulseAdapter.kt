package com.example.instagramstories.ui.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.instagramstories.databinding.PulseItemLayoutBinding
import com.example.instagramstories.remote.model.VideoDataModel
import com.squareup.picasso.Picasso


class PulseAdapter(private val videoList: List<VideoDataModel>) :
    RecyclerView.Adapter<PulseAdapter.ImageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = PulseItemLayoutBinding.inflate(inflater, parent, false)
        return ImageViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val image = videoList[position]
        holder.bind(image)
    }

    override fun getItemCount(): Int {

        Log.d("TAG", "getItemCount: " + videoList.size)
        return videoList.size
    }

    inner class ImageViewHolder(private val binding: PulseItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(image: VideoDataModel) {

            binding.apply {

                Picasso.get().load(image.image_url).into(pulseUserPic)

            }


        }
    }
}
