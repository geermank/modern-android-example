package com.geermank.data.cache.characters

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.geermank.data.models.CharacterDto

@Dao
interface CharactersDao {

    @Query("SELECT * FROM Characters WHERE id IN (:ids)")
    suspend fun getByIds(ids: List<Long>): List<CharacterDto>

    @Query("SELECT * FROM Characters c1 INNER JOIN (SELECT id, MIN(syncDate) FROM Characters GROUP BY id) c2 ON c1.id = c2.id")
    suspend fun getOldestCharacter(): CharacterDto?

    @Query("DELETE FROM Characters")
    suspend fun deleteAll()

    @Query("UPDATE Characters SET syncDate = :newSyncDate WHERE id IN (:characters)")
    suspend fun updateSyncDate(newSyncDate: Long, characters: List<Long>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun save(characters: List<CharacterDto>)
}