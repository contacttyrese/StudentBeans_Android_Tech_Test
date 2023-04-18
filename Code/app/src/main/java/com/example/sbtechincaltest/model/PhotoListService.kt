package com.example.sbtechincaltest.model

import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhotoListService {
    @GET("/photos")
    fun getPhotos(): Observable<List<Photo>>

    @GET("/photos")
    fun getPhotosByAlbumId(@Query("albumId") albumId: Int): Observable<List<Photo>>

    companion object {
        const val API_URL = "https://jsonplaceholder.typicode.com"
    }
}