package com.example.sbtechincaltest.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sbtechincaltest.databinding.PhotoRecyclerviewItemviewBinding
import com.example.sbtechincaltest.model.Photo

class PhotoRecyclerViewAdapter(private val photos: ArrayList<Photo>) : RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = PhotoRecyclerviewItemviewBinding.inflate(
            LayoutInflater.from(parent.context),
        parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        holder.bind(photo)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    class ViewHolder(private val binding: PhotoRecyclerviewItemviewBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(photo: Photo) = with(binding) {
            binding.photoItemTextView.text = photo.title
            Glide.with(binding.root).load(photo.thumbnailUrl).into(binding.photoItemImageView)
        }
    }
}