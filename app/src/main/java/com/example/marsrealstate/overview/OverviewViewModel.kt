package com.example.marsrealstate.overview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.marsrealstate.network.MarsApi
import com.example.marsrealstate.network.MarsProperty
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * The [ViewModel] that is attached to the [OverviewFragment].
 */
class OverviewViewModel : ViewModel() {

    // The internal MutableLiveData String that stores the most recent status
    private val _status = MutableLiveData<String>()

    // The external immutable LiveData for the status String
    val status: LiveData<String>
    get() = _status

    private val _property = MutableLiveData<MarsProperty>()
    val property: LiveData<MarsProperty>
    get() = _property


    // To use deferred instead of callback
    // With this we can easily update the value of our MutableLiveData when we get a result
    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    /**
     * Call getMarsRealEstateProperties() on init so we can display status immediately.
     */
    init {
        getMarsRealEstateProperties()
    }

    /**
     * Sets the value of the response LiveData to the Mars API status or
     * the successful number of Mars properties retrieved.
     * This method calls Retrofit service to handle the response
     */
    private fun getMarsRealEstateProperties() {
        // This is the default data
        _status.value = "Loading data"
        // CON CALLBACK EN EL MÉTODO ENQUEUE
//        // enqueue() start the network request on a background thread. Its parameter contains methods
//        // that can be invoked when the request is completed
//        MarsApi.retrofitService.getProperties().enqueue(object: Callback<List<MarsProperty>> {
//            override fun onResponse(
//                call: Call<List<MarsProperty>>,
//                response: Response<List<MarsProperty>>
//            ) {
//                _status.value = "${status.body()?.size}"
//
//            }
//
//            override fun onFailure(call: Call<List<MarsProperty>>, t: Throwable) {
//                _status.value = "Failure: ${t.message}"
//            }
//        })

        // CON CO-RUTINAS
        coroutineScope.launch {
            // This is a coroutine scope
            var getPropertiesDeferred = MarsApi.retrofitService.getProperties()
            try {
                var listResult = getPropertiesDeferred.await()
                // _status.value = "${listResult.size}"
                if (listResult.size > 0) {
                    _property.value = listResult[0]
                }
            } catch (t: Throwable) {
                _status.value = "Failure: ${t.message}"
            }
        }
    }

    override fun onCleared() {
        viewModelJob.cancel() // To cancel the job when the fragment is gone
        super.onCleared()
    }
}
