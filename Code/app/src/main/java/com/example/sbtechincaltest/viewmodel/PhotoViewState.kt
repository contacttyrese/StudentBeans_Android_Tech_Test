package com.example.sbtechincaltest.viewmodel

sealed class PhotoViewState {
    object Loading: PhotoViewState()
    object PhotoLoaded: PhotoViewState()
    object PhotoLoadError: PhotoViewState()
}