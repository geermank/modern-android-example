package com.geermank.rickandmorty.errors

import com.geermank.data.api.models.ErrorResponse
import com.geermank.data.repository.Repository
import com.geermank.rickandmorty.R
import com.geermank.rickandmorty.any
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class DataErrorMapperTest {

    private lateinit var repository: Repository
    private lateinit var dataErrorMapper: DataErrorMapper

    @Before
    fun setUp() {
        repository = mock(Repository::class.java)
        dataErrorMapper = DataErrorMapper(repository)
    }

    @Test
    fun `getting error model for network error returns an instance with correct and specific string resources`() {
        mockNetworkError()

        val errorModel = dataErrorMapper.getErrorModel(mock(Throwable::class.java))

        assertTrue(errorModel.isRecoverable)
        assertEquals(R.string.error_no_connection_title, errorModel.title)
        assertEquals(R.string.error_no_connection_subtitle, errorModel.cause)
    }

    @Test
    fun `getting error model for server error response returns an instance with correct and specific string resources`() {
        mockResponseError(500)

        val errorModel = dataErrorMapper.getErrorModel(mock(Throwable::class.java))

        assertTrue(errorModel.isRecoverable)
        assertEquals(R.string.error_server_side_title, errorModel.title)
        assertEquals(R.string.error_server_side_subtitle, errorModel.cause)
    }

    @Test
    fun `getting error model for client error response returns an instance with correct and specific string resources`() {
        mockResponseError(400)

        val errorModel = dataErrorMapper.getErrorModel(mock(Throwable::class.java))

        assertFalse(errorModel.isRecoverable)
        assertEquals(R.string.error_client_side_title, errorModel.title)
        assertEquals(R.string.error_client_side_subtitle, errorModel.cause)
    }

    @Test
    fun `getting error model for generic error returns generic string resources`() {
        mockGenericError()

        val errorModel = dataErrorMapper.getErrorModel(mock(Throwable::class.java))

        assertFalse(errorModel.isRecoverable)
        assertEquals(R.string.error_generic_title, errorModel.title)
        assertEquals(R.string.error_generic_subtitle, errorModel.cause)
    }

    private fun mockNetworkError() {
        `when`(repository.isNetworkError(any())).thenReturn(true)
        `when`(repository.isResponseError(any())).thenReturn(false)
    }

    private fun mockResponseError(errorCode: Int) {
        `when`(repository.isNetworkError(any())).thenReturn(false)
        `when`(repository.isResponseError(any())).thenReturn(true)
        `when`(repository.getErrorResponse(any())).thenReturn(ErrorResponse(errorCode, "Response message"))
    }

    private fun mockGenericError() {
        `when`(repository.isNetworkError(any())).thenReturn(false)
        `when`(repository.isResponseError(any())).thenReturn(false)
    }
}
