package com.android.reiffeisentest.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://randomuser.me/api/"
//endpoint example "https://randomuser.me/api/page=0&results=10&seed=abc"
private const val RESULTS_PER_PAGE = 20


private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ItemsApiService {
    @GET("?seed=abc&results=$RESULTS_PER_PAGE")
    suspend fun loadResults(
        @Query("page") page: Int
    ): Results
}

object ResultsApi {
    val retrofitService: ItemsApiService by lazy { retrofit.create(ItemsApiService::class.java) }
}