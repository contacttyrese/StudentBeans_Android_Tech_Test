package com.example.sbtechincaltest.model

import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Assert.*
import org.junit.Before

import org.junit.Test

class PhotoInteractorTest {
    private val repository = mockk<PhotoRepository>()
    private val observable = mockk<Observable<List<Photo>>>()
    private val scheduler = mockk<Scheduler>()
    private val interactor = PhotoInteractor(repository)

    @Before
    fun setUp() {
        mockkStatic(Schedulers::class)
        RxJavaPlugins.setInitIoSchedulerHandler { scheduler -> Schedulers.trampoline() }
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { scheduler -> Schedulers.trampoline()}
    }

    @Test
    fun `GIVEN getPhotosObservable THEN return Observable with photos`() {
        every { repository.fetchPhotosByAlbumId(any()) }.returns(observable)
        every { observable.subscribeOn(any()) }.returns(observable)
        every { observable.observeOn(any()) }.returns(observable)
        every { Schedulers.trampoline() }.returns(scheduler)

        val actual = interactor.getPhotosObservable()

        assertNotNull("observable was null", actual)
        assertEquals(observable, actual)
    }
}