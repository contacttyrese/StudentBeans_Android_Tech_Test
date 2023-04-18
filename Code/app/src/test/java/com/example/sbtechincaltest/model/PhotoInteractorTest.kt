package com.example.sbtechincaltest.model

import org.junit.Assert.*

import org.junit.Test

class PhotoInteractorTest {
    private val interactor = PhotoInteractor()

    @Test
    fun `GIVEN getPhotosObservable THEN return Observable with photos`() {
        lateinit var photo: Photo
        var sizeOfPhotos = 0

        var disposable = interactor.getPhotosObservable().
                subscribe { photos ->
                    photo = photos[0]
                    sizeOfPhotos = photos.size
                }

        assertNotNull("first photo was null", photo)
        assertTrue("size of photos is 0", sizeOfPhotos > 0)
        assertTrue("disposable was not disposed", disposable.isDisposed)
    }
}