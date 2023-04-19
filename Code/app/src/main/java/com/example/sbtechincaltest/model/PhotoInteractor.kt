package com.example.sbtechincaltest.model

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class PhotoInteractor constructor(
    private val repository: PhotoRepository
) {

    fun getPhotosObservable(): Observable<ArrayList<Photo>> {
        return repository.fetchPhotosByAlbumId(1)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}