package com.example.sbtechincaltest.viewmodel

import com.example.sbtechincaltest.model.Photo
import org.junit.Assert.*

import org.junit.Test

class PhotoViewModelTest {
    private val viewModel = PhotoViewModel()

    @Test
    fun `GIVEN getViewStateObservable THEN return observable`() {
        assertNotNull(viewModel.viewStateObservable)
    }

    @Test
    fun `GIVEN getViewState THEN return live data`() {
        assertNotNull(viewModel.viewState)
    }

    @Test
    fun `GIVEN getPhotosLiveData THEN return live data`() {
        assertNotNull(viewModel.photosLiveData)
    }

    @Test
    fun `GIVEN add photo to photos THEN verify size`() {
        val photo = Photo(1, 1, "a","a","a")

        viewModel.jsonPhotos.add(photo)

        assertNotNull(viewModel.jsonPhotos)
        assertEquals(1, viewModel.jsonPhotos.size)

    }

}