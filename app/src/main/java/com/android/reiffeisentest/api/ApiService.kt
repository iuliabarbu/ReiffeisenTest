package com.android.reiffeisentest.api

import retrofit2.http.GET
import retrofit2.http.Query

private const val RESULTS_PER_PAGE = 20

interface ApiService {
    @GET("?seed=abc&results=$RESULTS_PER_PAGE")
    suspend fun loadResults(
        @Query("page") page: Int
    ): Results
}