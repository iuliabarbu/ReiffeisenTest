package com.android.reiffeisentest.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.reiffeisentest.api.Results
import com.android.reiffeisentest.repository.ResultsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

enum class ResultsApiStatus { LOADING, ERROR, DONE }

@HiltViewModel
class ListViewModel @Inject constructor(
    private val resultsRepository: ResultsRepository
) : ViewModel() {
    val TAG = ListViewModel::class.java.simpleName as String

    private val _results = MutableLiveData<Results>()
    val results: LiveData<Results>
        get() = _results

    private val _status = MutableLiveData<ResultsApiStatus>()
    val status: LiveData<ResultsApiStatus>
        get() = _status

    private var currentPage: Int

    init {
        currentPage = -1
        getPaginatedResults()
    }

    fun getPaginatedResults() {
        //load just 3 pages and avoid multiple calls from onScrolled
        if (currentPage >= 2 || _status.value == ResultsApiStatus.LOADING) {
            return
        }

        viewModelScope.launch {
            _status.value = ResultsApiStatus.LOADING
            currentPage = currentPage.plus(1)
            try {
                val response = resultsRepository.loadPaginatedResults(currentPage)

                if (currentPage == 0) {
                    //load first page
                    _results.value = response
                } else {
                    // add objects for each new page
                    var temp = _results.value
                    response?.results?.let { temp?.results?.addAll(it) }
                    _results.value = temp
                }
                Log.d(TAG, " _results.value.results.size= " + _results.value?.results?.size)
                _status.value = ResultsApiStatus.DONE
            } catch (e: Exception) {
                e.printStackTrace()
                _status.value = ResultsApiStatus.ERROR
            }

        }
    }
}

