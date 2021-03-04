package com.geermank.data.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.geermank.data.cache.episodes.EpisodesDao
import com.geermank.data.models.EpisodeDto
import com.geermank.data.models.EpisodesPage
import javax.inject.Singleton

const val CURRENT_DATABASE_VERSION = 1
const val DATABASE_NAME = "LocalDatabase"

@TypeConverters(DatabaseConverter::class)
@Database(entities = [EpisodeDto::class, EpisodesPage::class], version = CURRENT_DATABASE_VERSION)
abstract class LocalDatabase : RoomDatabase() {

    companion object {
        private var sInstance: LocalDatabase? = null

        fun getInstance(context: Context) = sInstance ?: Room.databaseBuilder(
            context,
            LocalDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration()
            .build().also { sInstance = it }
    }

    abstract fun episodesDao(): EpisodesDao
}
