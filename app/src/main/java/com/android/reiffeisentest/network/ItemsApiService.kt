package com.android.reiffeisentest.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://randomuser.me/api/"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    //.add(TripsMoshiAdapter())
    .build()


private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface ItemsApiService {
    @GET("?page=0&results=10&seed=abc")
    suspend fun getItems(): Results
}

object TripsApi {
    val retrofitService: ItemsApiService by lazy { retrofit.create(ItemsApiService::class.java) }
}