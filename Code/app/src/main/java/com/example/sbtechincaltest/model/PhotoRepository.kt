package com.example.sbtechincaltest.model

import io.reactivex.Observable

class PhotoRepository constructor(
    private val service: PhotoListService
) {
    fun fetchPhotos(): Observable<ArrayList<Photo>> {
        return service.getPhotos() as Observable<ArrayList<Photo>>
    }

    fun fetchPhotosByAlbumId(albumId: Int): Observable<ArrayList<Photo>> {
        return service.getPhotosByAlbumId(albumId) as Observable<ArrayList<Photo>>
    }
}