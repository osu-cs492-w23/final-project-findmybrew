package com.example.findmybrew.api

import com.example.findmybrew.data.SingleBrewery
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface BrewerySearchService {
    @GET("search")
    suspend fun loadBrewerySearchResults(
        @Query("query") search: String?
    ) : Response<List<SingleBrewery>>

    companion object {
        private const val BASE_URL = "https://api.openbrewerydb.org/breweries/"

        fun create() : BrewerySearchService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build()
                .create(BrewerySearchService::class.java)
        }
    }
}