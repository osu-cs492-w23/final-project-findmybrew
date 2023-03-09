package com.example.findmybrew.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BreweryDao {
    @Insert
    suspend fun insert(beer: PhotoOfBeer)

    @Delete
    suspend fun delete(beer: PhotoOfBeer)

    @Query("SELECT * FROM PhotoOfBeer")
    fun getAllBeers(): Flow<List<PhotoOfBeer>>
}