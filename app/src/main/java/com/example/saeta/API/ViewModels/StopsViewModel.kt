package com.example.saeta.API.ViewModels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.saeta.API.RouteApi
import com.example.saeta.API.Stop
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

class StopsViewModel : ViewModel() {
    private val _goings = MutableLiveData<List<Stop>>()
    val goings: LiveData<List<Stop>> get() = _goings
    private val _returns = MutableLiveData<List<Stop>>()
    val returns: LiveData<List<Stop>> get() = _returns

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error


    fun fetchGoings(routeID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RouteApi.apiService.getGoings(routeID).execute()
                if (response.isSuccessful) {
                    response.body()?.let {
                        _goings.postValue(it)
                        _error.postValue(null)
                    } ?: run {
                        _error.postValue("No data available")
                    }
                } else {
                    _error.postValue("Failed to fetch data")
                }
            } catch (e: HttpException) {
                _error.postValue("HTTP error: ${e.message}")
            } catch (e: IOException) {
                _error.postValue("Network error: ${e.message}")
            } catch (e: Exception) {
                _error.postValue("Unexpected error: ${e.message}")
            }
        }
    }
    fun fetchReturns(routeID: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = RouteApi.apiService.getReturns(routeID).execute()
                if (response.isSuccessful) {
                    response.body()?.let {stop ->
                        _returns.postValue(stop)
                        _error.postValue(null)
                    } ?: run {
                        _error.postValue("No data available")
                    }
                } else {
                    _error.postValue("Failed to fetch data")
                }
            } catch (e: HttpException) {
                _error.postValue("HTTP error: ${e.message}")
            } catch (e: IOException) {
                _error.postValue("Network error: ${e.message}")
            } catch (e: Exception) {
                _error.postValue("Unexpected error: ${e.message}")
            }
        }
    }
}