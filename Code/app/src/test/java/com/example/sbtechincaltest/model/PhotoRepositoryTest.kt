package com.example.sbtechincaltest.model

import io.mockk.every
import io.mockk.mockk
import io.reactivex.Observable
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class PhotoRepositoryTest {
    private val service = mockk<PhotoListService>()
    private val observable = mockk<Observable<List<Photo>>>()
    private val repository = PhotoRepository(service)

    @Before
    fun setUp() {
        every { service.getPhotos() }.returns(observable)
        every { service.getPhotosByAlbumId(any()) }.returns(observable)
    }

    @Test
    fun `GIVEN fetchPhotos THEN return Observable with photos`() {
        val actual = repository.fetchPhotos()
        assertEquals(observable, actual)
    }

    @Test
    fun `GIVEN fetchPhotosByAlbumId THEN return Observable with photos`() {
        val actual = repository.fetchPhotosByAlbumId(1)
        assertEquals(observable, actual)
    }

    @Test
    fun `GIVEN fetchPhotosByAlbumId with id less than 0 THEN return Exception`() {
        assertThrows(Exception::class.java) {
            repository.fetchPhotosByAlbumId(-1)
        }
    }
}