package com.example.findmybrew.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.findmybrew.data.AppDatabase
import com.example.findmybrew.data.SavedBreweryRepository
import com.example.findmybrew.data.SingleBrewery
import kotlinx.coroutines.launch

class SavedBreweryViewModel (application: Application): AndroidViewModel(application) {
    private val repository = SavedBreweryRepository(
        AppDatabase.getInstance(application).breweryDao()
    )

    val breweries = repository.getAllBreweries().asLiveData()

    fun addBrewery(brewery: SingleBrewery) {
        viewModelScope.launch {
            repository.insertBrewery(brewery)
        }
    }

    fun removeBrewery(brewery: SingleBrewery) {
        viewModelScope.launch {
            repository.deleteBrewery(brewery)
        }
    }

    fun getBreweryByName(id: String)= repository.getBreweryByName(id)
}