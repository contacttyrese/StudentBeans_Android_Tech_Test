package com.example.sbtechincaltest.model

import io.reactivex.Observable

class PhotoRepository {
    fun fetchPhotos(): Observable<ArrayList<Photo>> {
        return PhotoListRetroFit.createPhotoListService().getPhotos() as Observable<ArrayList<Photo>>
    }

    fun fetchPhotosByAlbumId(albumId: Int): Observable<ArrayList<Photo>> {
        return PhotoListRetroFit.createPhotoListService().getPhotosByAlbumId(albumId) as Observable<ArrayList<Photo>>
    }
}