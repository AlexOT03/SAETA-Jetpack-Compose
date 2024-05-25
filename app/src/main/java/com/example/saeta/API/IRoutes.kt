package com.example.saeta.API

data class Route(
        val id: Int,
        val agency: String,
        val agency_logo: String,
        val short_name: Int,
        val long_name: String,
        val vehicle_type: String,
        val vehicle_img: String,
        val status: Boolean,
        val color: String,
        val terminal_1: Int,
        val terminal_2: Int,
        val route_type: String,
        val Going: List<Trip>,
        val Return: List<Trip>
)
data class Trip(
        val id: Int,
        val address: String,
        val stops: List<StopWrapper>
)
data class StopWrapper(
        val stop: Stop
)
data class Stop(
        val id: Int,
        val name: String,
        val road: String,
        val logo: String,
        val is_terminal: Boolean,
        val altitude: Number,
        val latitude: Number
)
data class Data(
        val routes: List<Route>
)