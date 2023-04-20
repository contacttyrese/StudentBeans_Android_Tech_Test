package com.example.sbtechincaltest.viewmodel

import com.example.sbtechincaltest.model.Photo

sealed class PhotoViewState {
    object Loading: PhotoViewState()
    data class PhotoLoaded(val photos: ArrayList<Photo>): PhotoViewState()
    object PhotoLoadError: PhotoViewState()
}