package com.android.reiffeisentest.repository

import com.android.reiffeisentest.api.ApiService
import com.android.reiffeisentest.api.Results
import javax.inject.Inject


class ResultsRepository @Inject constructor(
    private val apiService: ApiService
) {


    suspend fun loadPaginatedResults(curentPage: Int): Results {
        return apiService.loadResults(curentPage)
    }
}
