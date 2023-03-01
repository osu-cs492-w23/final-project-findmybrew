package com.example.findmybrew.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.findmybrew.api.BrewerySearchService
import com.example.findmybrew.data.BreweryRepository
import com.example.findmybrew.data.BrewerySearchResults
import kotlinx.coroutines.launch

class BreweryViewModel: ViewModel() {
    private val repository = BreweryRepository(BrewerySearchService.create())

    private val _forecast = MutableLiveData<BrewerySearchResults?>(null)
    val forecast: LiveData<BrewerySearchResults?> = _forecast

    private val _error = MutableLiveData<Throwable?>(null)
    val error: LiveData<Throwable?> = _error

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    fun loadFiveDayForecast(search: String?) {
        /*
         * Launch a new coroutine in which to execute the API call.  The coroutine is tied to the
         * lifecycle of this ViewModel by using `viewModelScope`.
         */
        viewModelScope.launch {
            _loading.value = true
            val result = repository.loadBrewerySearchResults(search)
            _loading.value = false
            _error.value = result.exceptionOrNull()
            _forecast.value = result.getOrNull()
        }
    }
}