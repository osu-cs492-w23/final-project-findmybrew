package com.example.findmybrew.data

import androidx.lifecycle.asLiveData

class SavedBreweryRepository(private val dao: BreweryDao) {

    suspend fun insertBrewery(brewery: SingleBrewery) = dao.insert(brewery)
    suspend fun deleteBrewery(brewery: SingleBrewery) = dao.delete(brewery)
    fun getAllBreweries() = dao.getAllBreweries()
    fun getBreweryByName(id: String) = dao.getBreweryByName(id).asLiveData()
}