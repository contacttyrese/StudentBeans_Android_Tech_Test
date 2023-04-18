package com.example.sbtechincaltest.model

import org.junit.Assert.*

import org.junit.Before
import org.junit.Test

class PhotoListRetroFitTest {
    private val retroFit = PhotoListRetroFit

    @Test
    fun `GIVEN instantiation THEN object not null`() {
        assertNotNull(retroFit)
    }

    @Test
    fun `GIVEN create service THEN return photo list service`() {
        val actual = retroFit.createPhotoListService()

        assertTrue("service is not photo list service", actual is PhotoListService)
    }
}