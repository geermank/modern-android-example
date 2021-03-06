package com.geermank.data.api

import javax.inject.Inject

class ApiUrlEntityIdExtractor @Inject constructor() {

    private val pattern = "([0-9]+)"

    /**
     * Receives a list of API urls and returns a list of entities id
     */
    fun extractEntityId(urls: List<String>): List<Long> {
        return urls.map { getIdFromUrl(it) }
    }

    private fun getIdFromUrl(url: String): Long {
        return Regex(pattern).find(url)?.value?.toLong() ?: 0
    }
}
