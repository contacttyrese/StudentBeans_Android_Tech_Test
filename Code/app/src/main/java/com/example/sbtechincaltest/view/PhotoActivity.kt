package com.example.sbtechincaltest.view

import android.os.Bundle
import android.util.Log
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sbtechincaltest.R
import com.example.sbtechincaltest.model.Photo
import com.example.sbtechincaltest.viewmodel.PhotoUserAction
import com.example.sbtechincaltest.viewmodel.PhotoViewModel
import com.example.sbtechincaltest.viewmodel.PhotoViewState

class PhotoActivity : AppCompatActivity() {
//    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: PhotoViewModel

    private lateinit var heading: TextView
    private lateinit var recyclerView: RecyclerView

    private lateinit var _photos: ArrayList<Photo>
    var photos = ArrayList<Photo>()
    get() = _photos

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO binding investigation time consuming
//        binding = ActivityMainBinding.inflate(layoutInflater)
//        val view = binding.root
//        setContentView(view)
        setContentView(R.layout.activity_photo)
        viewModel = ViewModelProvider(this)[PhotoViewModel::class.java]

        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)

        createViews()
        configureRecycler()
        createObservation()
    }

    override fun onStart() {
        super.onStart()
        viewModel.userActionSubjectOnNextWithPhotoUserAction(PhotoUserAction.Process)
    }

    private fun createViews() {
        heading = findViewById(R.id.photosHeading)
        recyclerView = findViewById(R.id.photosRecycler)
    }

    private fun configureRecycler() {
        _photos = viewModel.jsonPhotos
        recyclerView.adapter = PhotoRecyclerViewAdapter(this, _photos)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.isNestedScrollingEnabled = true
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.setHasFixedSize(true)
        recyclerView.setItemViewCacheSize(3)
        recyclerView.findViewHolderForItemId(0)
    }

    private fun createObservation() {
        viewModel.photosLiveData.observe(this) {
            _photos.removeAll(_photos.toSet())
            _photos.addAll(it)
            recyclerView.adapter?.let {
                it.notifyDataSetChanged()
                Log.i("recycler_update_success", "sent notification of data change in recylcer")
            } ?: kotlin.run {
                Log.e("recycler_update_fail", "recycler was null so notification not sent")
            }
        }

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
                    Log.i("activity_photo_loaded", "photos loaded")
                }
            }
        }

    }

    override fun onDestroy() {
        super.onDestroy()
//        viewModel.clearDisposables()
    }

}