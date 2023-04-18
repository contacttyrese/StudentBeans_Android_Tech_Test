package com.example.sbtechincaltest.model

import org.junit.Assert.*

import org.junit.Test

class PhotoRepositoryTest {
    private val repository = PhotoRepository()

    @Test
    fun `GIVEN fetchPhotos THEN return Observable with photos`() {
        lateinit var photo: Photo
        var sizeOfPhotos = 0

        var disposable = repository.fetchPhotos()
            .subscribe{ photos ->
                photo = photos[0]
                sizeOfPhotos = photos.size
            }
        disposable.dispose()

        assertNotNull("first photo was null", photo)
        assertTrue("size of photos is 0", sizeOfPhotos > 0)
        assertTrue("disposable was not disposed", disposable.isDisposed)
    }

    @Test
    fun `GIVEN fetchPhotosByAlbumId THEN return Observable with photos`() {
        lateinit var photo: Photo
        var sizeOfPhotos = 0

        val albumId = 4
        var disposable = repository.fetchPhotosByAlbumId(albumId)
            .subscribe { photos ->
                photo = photos[0]
                sizeOfPhotos = photos.size
            }
        disposable.dispose()

        assertNotNull("first photo was null", photo)
        assertTrue("size of photos is 0", sizeOfPhotos > 0)
        assertTrue("disposable was not disposed", disposable.isDisposed)
    }
}