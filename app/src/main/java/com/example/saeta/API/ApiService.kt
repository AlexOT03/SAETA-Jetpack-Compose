package com.example.saeta.API

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("routes")
    fun getRoutes(): Call<List<Route>>
}