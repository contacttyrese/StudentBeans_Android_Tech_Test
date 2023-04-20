package com.example.sbtechincaltest.model

import org.junit.Assert.*
import org.junit.Test

class PhotoTest {
    @Test
    fun `GIVEN instantiation test THEN return photo`() {
        val actual = Photo(0, 0, "a", "a", "a")
        assertNotNull(actual)
    }
}