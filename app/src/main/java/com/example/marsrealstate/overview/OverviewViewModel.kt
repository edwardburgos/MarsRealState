package com.example.marsrealstate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marsrealstate.network.MarsApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent response
    private val _response = MutableLiveData<String>()

    // The external immutable LiveData for the response String
    val response: LiveData<String>
        get() = _response

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or
     * the successful number of Mars properties retrieved.
     */
    private fun getMarsRealEstateProperties() {
        _response.value = "Set the Mars API Response here!"
    }
}
