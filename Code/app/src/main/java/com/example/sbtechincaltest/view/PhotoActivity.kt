package com.example.sbtechincaltest.view

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sbtechincaltest.databinding.ActivityPhotoBinding
import com.example.sbtechincaltest.model.Photo
import com.example.sbtechincaltest.viewmodel.PhotoViewModel
import com.example.sbtechincaltest.viewmodel.PhotoViewState
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoActivity : AppCompatActivity() {
    private val viewModel: PhotoViewModel by viewModels()

    private lateinit var binding: ActivityPhotoBinding
    private val _photos = ArrayList<Photo>()
    var photos = ArrayList<Photo>()
    get() = _photos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        configureRecycler()
        createObservation()
    }

    private fun configureRecycler() {
        binding.photosRecycler.adapter = PhotoRecyclerViewAdapter(_photos)
        binding.photosRecycler.layoutManager = LinearLayoutManager(this)
        binding.photosRecycler.isNestedScrollingEnabled = true
        binding.photosRecycler.itemAnimator = DefaultItemAnimator()
        binding.photosRecycler.setHasFixedSize(true)
        binding.photosRecycler.setItemViewCacheSize(3)
        binding.photosRecycler.findViewHolderForItemId(0)
    }

    private fun createObservation() {
        viewModel.viewState.observe(this) { state ->
            when (state) {
                PhotoViewState.Loading -> {
                    ProgressBar.VISIBLE
                    Log.i("activity_photo", "photos loading")
                }
                is PhotoViewState.PhotoLoadError -> {
                    ProgressBar.GONE
                    Log.e("activity_photo_error", "photos loading error")
                }
                is PhotoViewState.PhotoLoaded -> {
                    ProgressBar.GONE
                    _photos.removeAll(_photos.toSet())
                    _photos.addAll(state.photos)
                    binding.photosRecycler.adapter?.let {
                        it.notifyDataSetChanged()
                        Log.i("recycler_update_success", "sent notification of data change in recylcer")
                    } ?: kotlin.run {
                        Log.e("recycler_update_fail", "recycler was null so notification not sent")
                    }
                    Log.i("activity_photo_loaded", "photos loaded")
                }
            }
        }

    }
}