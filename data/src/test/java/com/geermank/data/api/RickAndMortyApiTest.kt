package com.geermank.data.api

import org.junit.Assert.assertEquals
import org.junit.Test

class RickAndMortyApiTest {

    private val api = RickAndMortyApi()

    @Test
    fun `getting api url returns the correct value`() {
        assertEquals("https://rickandmortyapi.com/api/", api.getBaseUrl())
    }
}
