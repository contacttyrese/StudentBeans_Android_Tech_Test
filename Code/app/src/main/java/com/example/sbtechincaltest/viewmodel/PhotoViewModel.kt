package com.example.sbtechincaltest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sbtechincaltest.model.Photo
import com.example.sbtechincaltest.model.PhotoInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val interactor: PhotoInteractor,
    private val disposables: CompositeDisposable
) : ViewModel() {
    private val _viewState = MutableLiveData<PhotoViewState>()
    val viewState: LiveData<PhotoViewState>
        get() = _viewState
    private var _photosMutableLiveData = MutableLiveData<List<Photo>>()
    val photosLiveData: LiveData<List<Photo>>
        get() = _photosMutableLiveData

    init {
        _viewState.postValue(PhotoViewState.Loading)
        disposables.add(
            interactor.getPhotosObservable()
                .subscribeOn(Schedulers.io())
                .subscribe ({ list ->
                    val didListContainEmptyPhoto = (list as ArrayList<Photo>).removeAll() { photo ->
                        photo.title.isBlank() || photo.thumbnailUrl.isBlank()
                    }
                    Log.i("check_if_empty_in_list", "did list contain empty photo: $didListContainEmptyPhoto")
                    if (list.isNotEmpty()) {
                        processPhotos(list)
                    } else {
                        Log.e("check_list_size", "list size is not more than 0")
                    }
                }, {
                    _viewState.postValue(PhotoViewState.PhotoLoadError)
                    Log.e("process_sub_viewmodel", "error occurred $it")
                }
                )
        )
    }

    private fun clearDisposables() {
        disposables.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        clearDisposables()
    }

    private fun processPhotos(list: ArrayList<Photo>) {
        Log.i("process_photo_sub", "photos from it are ${list.size}")
        _viewState.postValue(PhotoViewState.PhotoLoaded(list))
        _photosMutableLiveData.postValue(list)
    }
}