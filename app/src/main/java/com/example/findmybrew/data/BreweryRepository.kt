package com.example.findmybrew.data

import com.example.findmybrew.api.BrewerySearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreweryRepository (
    private val service: BrewerySearchService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private var cachedBreweries: BrewerySearchResults? = null

    suspend fun loadBrewerySearchResults(
        search: String?
    ) : Result<BrewerySearchResults?> {
        /*
         * If we have a cached forecast for the same location and units, return the cached forecast
         * without making a network call.  Otherwise, make an API call to fetch the forecast and
         * cache it.
         */
        return if (cachedBreweries!= null) {
            Result.success(cachedBreweries!!)
        } else {
            withContext(ioDispatcher) {
                try {
                    val response = service.loadBrewerySearchResults(search)
                    if (response.isSuccessful) {
                        cachedBreweries = response.body()
                        Result.success(cachedBreweries)
                    } else {
                        Result.failure(Exception(response.errorBody()?.string()))
                    }
                } catch (e: Exception) {
                    Result.failure(e)
                }
            }
        }
    }
}