package com.example.saeta.API.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.saeta.API.Data
import com.example.saeta.API.Route
import com.example.saeta.API.RouteApi
import kotlinx.coroutines.Dispatchers
import retrofit2.HttpException

class RouteViewModel : ViewModel(){
    val routes = liveData(Dispatchers.IO){
       try {
           val response = RouteApi.apiService.getRoutes().execute()
           if (response.isSuccessful){
               response.body()?.let{
                   emit(it)
               } ?: emit(emptyList<Route>())
           }else{
               emit(emptyList<Route>())
           }
       }catch (e:HttpException){
           emit(emptyList<Route>())
       }
    }
}