package com.example.findmybrew.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BrewerySearchResults(
    val list: List<SingleBrewery>
) :java.io.Serializable
