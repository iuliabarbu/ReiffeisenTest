package com.android.reiffeisentest.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.reiffeisentest.network.Results
import com.android.reiffeisentest.network.ResultsApi
import kotlinx.coroutines.launch

enum class ResultsApiStatus { LOADING, ERROR, DONE }

class ListViewModel : ViewModel() {
    val TAG = ListViewModel::class.java.simpleName as String

    private val _results = MutableLiveData<Results>()
    val results: LiveData<Results>
        get() = _results

    private val _status = MutableLiveData<ResultsApiStatus>()
    val status: LiveData<ResultsApiStatus>
        get() = _status


    init {
        getResults()
    }

    private fun getResults() {
        viewModelScope.launch {
            _status.value = ResultsApiStatus.LOADING
            try {
                var resultsRows = ResultsApi.retrofitService.getResults()
                _results.value = resultsRows
                _status.value = ResultsApiStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = ResultsApiStatus.ERROR
            }
        }
    }
}