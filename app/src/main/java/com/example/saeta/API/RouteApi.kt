package com.example.saeta.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RouteApi {
    //Cambiar por la IP de tu equipo
    private const val BASE_URL = "http://10.0.0.13:3000/Api/"
    val apiService: ApiService by lazy {
        try {
            Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                    .create(ApiService::class.java)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        } ?: throw IllegalStateException("Failed to create ApiService")
    }
}