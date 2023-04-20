package com.example.sbtechincaltest.viewmodel

import androidx.lifecycle.LiveData
import com.example.sbtechincaltest.model.Photo
import com.example.sbtechincaltest.model.PhotoInteractor
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.reactivex.disposables.CompositeDisposable
import org.junit.Assert.*

import org.junit.Test

class PhotoViewModelTest {
    private val interactor = mockk<PhotoInteractor>()
    private val disposables = mockk<CompositeDisposable>()
    private val viewState = mockk<LiveData<PhotoViewState>>()
    private val photosLiveData = mockk<LiveData<List<Photo>>>()
    private val viewModel = spyk(PhotoViewModel(interactor, disposables))

    @Test
    fun `GIVEN getViewState THEN return live data`() {
        every { viewModel.viewState }.returns(viewState)
        val actual = viewModel.viewState

        assertNotNull(viewModel.viewState)
        assertEquals(viewState, actual)
    }

    @Test
    fun `GIVEN getPhotosLiveData THEN return live data`() {
        every { viewModel.photosLiveData }.returns(photosLiveData)
        val actual = viewModel.photosLiveData

        assertNotNull(viewModel.photosLiveData)
        assertEquals(viewState, actual)
    }
}