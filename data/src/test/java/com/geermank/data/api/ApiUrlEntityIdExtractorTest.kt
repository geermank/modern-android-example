package com.geermank.data.api

import org.junit.Assert.assertEquals
import org.junit.Test

class ApiUrlEntityIdExtractorTest {

    private val apiUrlEntityIdExtractor = ApiUrlEntityIdExtractor()

    @Test
    fun `given a list of api urls with numeric ids, returns a list of only the ids`() {
        val urls = listOf(
            "https://rickandmortyapi.com/api/213",
            "https://rickandmortyapi.com/api/92",
            "https://rickandmortyapi.com/api/3"
        )

        val ids = apiUrlEntityIdExtractor.extractEntityId(urls)

        assertEquals(213, ids[0])
        assertEquals(92, ids[1])
        assertEquals(3, ids[2])
    }

    @Test
    fun `given a list of api urls without numbers, returns a list of zeros`() {
        val urls = listOf(
            "https://rickandmortyapi.com/api/locations",
            "https://rickandmortyapi.com/api/characters",
            "https://rickandmortyapi.com/api/episodes"
        )

        val ids = apiUrlEntityIdExtractor.extractEntityId(urls)

        assertEquals(0, ids[0])
        assertEquals(0, ids[1])
        assertEquals(0, ids[2])
    }

}