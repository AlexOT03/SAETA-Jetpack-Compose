package com.example.saeta.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RouteApi {
    private const val BASE_URL = "http://10.0.0.13:3000/Api/"
    val apiService: ApiService by lazy {
        Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService::class.java)
    }
}