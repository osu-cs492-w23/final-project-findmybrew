package com.example.findmybrew.ui

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.findmybrew.data.AppDatabase
import com.example.findmybrew.data.PhotoOfBeer
import com.example.findmybrew.data.PhotosOfBeersRepository
import com.example.findmybrew.data.SingleBrewery
import kotlinx.coroutines.launch

class PhotosOfBeersViewModel (application: Application): AndroidViewModel(application) {
    private val repository = PhotosOfBeersRepository(
        AppDatabase.getInstance(application).breweryDao()
    )

    val beer = repository.getAllPhotosOfBeers().asLiveData()

    fun addPhotoofBeer(beer: PhotoOfBeer) {
        viewModelScope.launch {
            repository.insertPhotoOfBeer(beer)
        }
    }

    fun removePhotoOfBeer(beer: PhotoOfBeer) {
        viewModelScope.launch {
            repository.deletePhotoOfBeer(beer)
        }
    }
}