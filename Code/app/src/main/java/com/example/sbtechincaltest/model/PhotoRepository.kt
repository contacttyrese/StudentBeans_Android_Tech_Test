package com.example.sbtechincaltest.model

import io.reactivex.Observable
import javax.inject.Inject

class PhotoRepository @Inject constructor(
    private val service: PhotoListService
) {
    fun fetchPhotos(): Observable<List<Photo>> {
        return service.getPhotos()
    }

    fun fetchPhotosByAlbumId(albumId: Int): Observable<List<Photo>> {
        return if (albumId < 0) {
            throw Exception("album id is less than one")
        } else {
            service.getPhotosByAlbumId(albumId)
        }
    }
}