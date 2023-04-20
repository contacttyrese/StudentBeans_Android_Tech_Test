package com.example.sbtechincaltest.model

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PhotoInteractor @Inject constructor(
    private val repository: PhotoRepository
) {

    fun getPhotosObservable(): Observable<List<Photo>> {
        return repository.fetchPhotosByAlbumId(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}