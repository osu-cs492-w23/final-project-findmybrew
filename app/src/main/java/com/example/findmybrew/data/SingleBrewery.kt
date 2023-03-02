package com.example.findmybrew.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SingleBrewery(
    val id: String,
    val name: String,
    val street: String,
    val city: String,
    val state: String,
    val postal_code: String,
    @Json(name = "longitude") val lon: String,
    @Json(name = "latitude") val lat: String,
    val phone: String,
    val website_url: String
) : java.io.Serializable
