package com.example.sbtechincaltest.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.sbtechincaltest.model.Photo
import com.example.sbtechincaltest.model.PhotoInteractor
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val interactor: PhotoInteractor,
    private val userActionSubject: PublishSubject<PhotoUserAction>,
    private val viewStateSubject: BehaviorSubject<PhotoViewState>,
    private val _viewState: MutableLiveData<PhotoViewState>,
    private var _photosMutableLiveData: MutableLiveData<ArrayList<Photo>>,
    private var _jsonPhotos: ArrayList<Photo>,
    private val disposables: CompositeDisposable
) : ViewModel() {
    val userActionObservable: Observable<PhotoUserAction>
        get() = userActionSubject
    val viewStateObservable: Observable<PhotoViewState>
        get() = viewStateSubject
    val viewState: LiveData<PhotoViewState>
        get() = _viewState
    val photosLiveData: LiveData<ArrayList<Photo>>
        get() = _photosMutableLiveData
    val jsonPhotos
        get() = _jsonPhotos

    init {
        disposables.add(
        userActionSubject
            .subscribeOn(Schedulers.io())
            .subscribe({
                when (it) {
                    is PhotoUserAction.Process -> {
                        Log.i("viewmodel_sub", "subscribed to process")
                        viewStateSubject.onNext(PhotoViewState.Loading)
                        disposables.add(
                        interactor.getPhotosObservable()
                            .subscribe({ list ->
                                val didListContainEmptyPhoto = list.removeAll { photo ->
                                    photo.title.isNullOrBlank() || photo.thumbnailUrl.isNullOrBlank()
                                }
                                Log.i("check_if_empty_in_list", "did list contain empty photo: $didListContainEmptyPhoto")
                                processPhotos(list)
                            }, {
                                Log.e("process_sub_viewmodel", "error occurred $it")
                            })
                        )
                    }
                }
            }, {
                Log.e("action_sub_viewmodel", "error occurred $it")
            })
        )
    }

    private fun clearDisposables() {
        disposables.dispose()
    }

    override fun onCleared() {
        super.onCleared()
        userActionSubject.onComplete()
        viewStateSubject.onComplete()
        clearDisposables()
    }

    private fun processPhotos(list: ArrayList<Photo>) {
        Log.i("process_photo_sub", "photos from it are ${list.size}")
        viewStateSubject.onNext(PhotoViewState.PhotoLoaded)
        _photosMutableLiveData.postValue(list)
    }

    fun userActionSubjectOnNextWithPhotoUserAction(photoUserAction: PhotoUserAction){
        userActionSubject.onNext(photoUserAction)
    }
}