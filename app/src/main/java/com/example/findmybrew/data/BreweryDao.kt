package com.example.findmybrew.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface BreweryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(brewery: SingleBrewery)

    @Delete
    suspend fun delete(brewery: SingleBrewery)

    @Query("SELECT * FROM SingleBrewery")
    fun getAllBreweries(): Flow<List<SingleBrewery>>

    @Query("SELECT * FROM SingleBrewery WHERE id = :id LIMIT 1")
    fun getBreweryByName(id: String): Flow<SingleBrewery?>
}