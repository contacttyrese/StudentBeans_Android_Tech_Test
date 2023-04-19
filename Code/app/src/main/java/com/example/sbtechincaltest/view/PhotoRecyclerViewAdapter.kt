package com.example.sbtechincaltest.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.model.Photo

class PhotoRecyclerViewAdapter(context: Context, private val photos: ArrayList<Photo>) : RecyclerView.Adapter<PhotoRecyclerViewAdapter.ViewHolder>() {
    private val inflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = inflater.inflate(R.layout.photo_recyclerview_itemview, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val photo = photos[position]
        holder.createPhotoView()
        holder.populatePhotoView(photo)
    }

    override fun getItemCount(): Int {
        return photos.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private lateinit var imageView: ImageView
        private lateinit var textView: TextView

        fun createPhotoView() {
            imageView = itemView.findViewById(R.id.photoItemImageView)
            textView = itemView.findViewById(R.id.photoItemTextView)
        }

        fun populatePhotoView(photo: Photo) {
            textView.text = photo.title
            Glide.with(itemView).load(photo.thumbnailUrl).into(imageView)
        }

    }
}