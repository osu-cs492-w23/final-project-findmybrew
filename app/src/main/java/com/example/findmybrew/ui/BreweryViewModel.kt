package com.example.findmybrew.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmybrew.api.BrewerySearchService
import com.example.findmybrew.data.BreweryRepository
import com.example.findmybrew.data.SingleBrewery
import kotlinx.coroutines.launch

class BreweryViewModel: ViewModel() {
    private val repository = BreweryRepository(BrewerySearchService.create())

    private val _brewery = MutableLiveData<List<SingleBrewery>?>(null)
    val brewery: LiveData<List<SingleBrewery>?> = _brewery

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?> = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    fun loadBrewerySearch(search: String?) {
        viewModelScope.launch {
            _loading.value = true
            val result = repository.loadBrewerySearchResults(search)
            _loading.value = false
            _error.value = result.exceptionOrNull()
            _brewery.value = result.getOrNull()
        }
    }
}