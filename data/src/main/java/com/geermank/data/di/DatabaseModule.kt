package com.geermank.data.di

import android.content.Context
import com.geermank.data.cache.episodes.EpisodesDao
import com.geermank.data.cache.database.LocalDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@InstallIn(ApplicationComponent::class)
@Module
object DatabaseModule {

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LocalDatabase {
        return LocalDatabase.getInstance(context)
    }

    @Provides
    fun provideEpisodesDao(database: LocalDatabase): EpisodesDao {
        return database.episodesDao()
    }
}
