package com.android.reiffeisentest.list

import android.util.Log
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

    private val _currentPage = MutableLiveData<Int>()
    val currentPage: LiveData<Int>
        get() = _currentPage

    init {
        _currentPage.value = -1
        getPaginatedResults()
    }

    fun getPaginatedResults() {
        //load just 3 pages
        if (currentPage.value!! >= 2 || _status.value == ResultsApiStatus.LOADING) {
            Log.d(TAG, " return " + _currentPage.value + " -" + _status.value )
            return
        }

        _currentPage.value = _currentPage.value?.plus(1)

        viewModelScope.launch {
            _status.value = ResultsApiStatus.LOADING
            try {
                var response = _currentPage.value?.let {
                    ResultsApi.retrofitService.loadResults(it)
                }

                if (_currentPage.value!! == 0) {
                    _results.value = response
                } else {
                    var temp = _results.value
                    response?.results?.let { temp?.results?.addAll(it) }
                    _results.value = temp
                }
                Log.d(TAG, " _results.value.results.size= " + _results.value?.results?.size )
                _status.value = ResultsApiStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = ResultsApiStatus.ERROR
            }
        }
    }
}

