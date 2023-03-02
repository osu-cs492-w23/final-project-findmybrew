package com.example.findmybrew.data

import com.example.findmybrew.api.BrewerySearchService
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class BreweryRepository (
    private val service: BrewerySearchService,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    private var cachedBreweries: List<SingleBrewery>? = null

    suspend fun loadBrewerySearchResults(
        search: String?
    ) : Result<List<SingleBrewery>?> {

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