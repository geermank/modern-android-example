package com.geermank.data.di

import android.content.Context
import org.junit.Assert.assertNotNull
import org.junit.Test
import org.mockito.Mockito.mock

class DatabaseModuleTest {

    @Test
    fun `given a context, the module provides a LocalDatabase instance`() {
        val context = mock(Context::class.java)
        assertNotNull(DatabaseModule.provideDatabase(context))
    }

    @Test
    fun `given a LocalDatabase, the module provides a not null charactersDao instance`() {
        val context = mock(Context::class.java)
        val database = DatabaseModule.provideDatabase(context)
        assertNotNull(DatabaseModule.provideCharactersDao(database))
    }

    @Test
    fun `given a LocalDatabase, the module provides a not null episodesDao instance`() {
        val context = mock(Context::class.java)
        val database = DatabaseModule.provideDatabase(context)
        assertNotNull(DatabaseModule.provideEpisodesDao(database))
    }
}