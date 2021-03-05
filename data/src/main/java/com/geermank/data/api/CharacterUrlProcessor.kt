package com.geermank.data.api

import javax.inject.Inject

class CharacterUrlProcessor @Inject constructor() {

    /**
     * Receives a list of API urls containing the id of the character
     * and returns a list of ids
     */
    fun process(urls: List<String>): List<Long> {
        return urls.map { it.last().toLong() }
    }
}
