package com.example.findmybrew.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class PhotoOfBeer(
    val breweryId: String,
    @PrimaryKey
    val photoPath: String,
    val name: String
)
