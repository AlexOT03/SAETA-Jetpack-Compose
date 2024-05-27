package com.example.saeta.RutasScreen

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.ModalBottomSheetProperties
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.saeta.API.Route
import com.example.saeta.API.Stop
import com.example.saeta.API.ViewModels.RouteViewModel
import com.example.saeta.API.ViewModels.StopsViewModel
import com.example.saeta.Components.SearchText
import com.example.saeta.R
import com.example.saeta.TopBar.Screens
import com.example.saeta.ui.theme.secondaryLight
import com.example.saeta.ui.theme.tertiaryLight
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState
import org.locationtech.proj4j.CRSFactory
import org.locationtech.proj4j.CoordinateReferenceSystem
import org.locationtech.proj4j.ProjCoordinate
import java.lang.Error


data class Ruta(val id: Int, val name: String)

@Composable
fun RutasScreen(routeViewModel: RouteViewModel = viewModel()) {
    val routes by routeViewModel.routes.observeAsState(emptyList())
    val error by routeViewModel.error.observeAsState()
    val navController = rememberNavController()

    NavHost(
            navController, startDestination = "ruta_screen",
    ) {
        composable("ruta_screen") { RutaListScreen(navController, routes, error) }
        composable(Screens.DetailRouteScreen.route) { backStackEntry ->
            val rutaId = backStackEntry.arguments?.getString("rutaId")?.toInt() ?: 1
            RutaDetailScreen(rutaId)
        }
        composable("loading_screen/{rutaId}") { backStackEntry ->
            val rutaId = backStackEntry.arguments?.getString("rutaId")?.toInt() ?: 1
            LoadingScreenWithDelay(navController, rutaId)
        }
    }
}

@Composable
fun LoadingScreenWithDelay(navController: NavHostController, rutaId: Int) {
    LaunchedEffect(Unit) {
        kotlinx.coroutines.delay(2000) // Simula un retraso de 2 segundos
        navController.navigate("ruta_detail/$rutaId") {
            popUpTo("loading_screen/{rutaId}") { inclusive = true }
        }
    }
    LoadingScreen()
}

@SuppressLint("UnrememberedMutableState")
@Composable
fun RutaListScreen(navController: NavHostController, routes: List<Route>, error: String?) {

    val textValue = remember { mutableStateOf("") }
    val filteredRutas by derivedStateOf {
        routes.filter { it.long_name.contains(textValue.value, ignoreCase = true) }
    }

    Column(
            modifier = Modifier
                    .padding(end = 20.dp, start = 20.dp)
                    .fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(10.dp))
        SearchText(textValue = textValue)
        Spacer(modifier = Modifier.height(5.dp))
        Spacer(modifier = Modifier.height(10.dp))

        if (error != null) {

            Text(
                    text = "No se ha podido conectar con la API",
                    color = Color.Red,
                    modifier = Modifier
                            .padding(top = 100.dp)
                            .align(Alignment.CenterHorizontally)
            )
        } else if (routes.isEmpty()) {
            CircularProgressIndicator(
                    modifier = Modifier
                            .size(70.dp)
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 100.dp),
                    color = secondaryLight,
                    strokeWidth = 6.dp,

                    )
        } else {
            lazyColumnList(filteredRutas = filteredRutas, navController = navController)
        }

    }
}

@Composable
fun lazyColumnList(filteredRutas: List<Route>, navController: NavHostController) {
    LazyColumn {
        items(filteredRutas) { ruta ->
            itemList(ruta, navController)
        }
    }
}

@Composable
fun itemList(ruta: Route, navController: NavHostController) {
    Card(
            modifier = Modifier
                    .fillMaxWidth()
                    .clickable { navController.navigate("loading_screen/${ruta.id}") }
                    .padding(vertical = 5.dp),
            elevation = CardDefaults.cardElevation(4.dp),
            colors = CardDefaults.cardColors(
                    containerColor = Color.White
            )
    ) {
        Row {
            Image(
                    painter = painterResource(id = R.drawable.icon_route),
                    contentDescription = null,
                    modifier = Modifier.size(70.dp),
                    colorFilter = ColorFilter.tint(tertiaryLight)
            )
            Column(
                    modifier = Modifier.padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start,
            ) {
                Text(ruta.long_name)
                Text("Ver Horarios")
            }
        }
    }
}

@Composable
fun RutaDetailScreen(routeID: Int, stopViewModel: StopsViewModel = viewModel()) {
    val goings by stopViewModel.goings.observeAsState(emptyList())
    val returns by stopViewModel.returns.observeAsState(emptyList())
    val error by stopViewModel.error.observeAsState()

    var isLoading by remember { mutableStateOf(false) }
    LaunchedEffect(routeID) {
        stopViewModel.fetchGoings(routeID)
        stopViewModel.fetchReturns(routeID)
    }
    var selectedTabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Ida", "Vuelta")
    Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .padding(2.dp)
    ) {
        if (error != null) {
            Text(text = "Error: $error", color = Color.Red)
        } else {
            // Mostrar el TabRow si no hay error
            TabRow(selectedTabIndex,
                    indicator = { tabPositions ->
                        TabRowDefaults.Indicator(
                                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
                        )
                    }
            ) {
                tabs.forEachIndexed { index, title ->
                    Tab(
                            text = { Text(title) },
                            selected = selectedTabIndex == index,
                            onClick = { selectedTabIndex = index },
                            icon = {
                                Icon(painter = painterResource(id = R.drawable.icon_home), contentDescription = title)
                            },

                            )
                }
            }
            LaunchedEffect(selectedTabIndex) {
                isLoading = true
                kotlinx.coroutines.delay(2000) // Simula un retraso de 2 segundos
                isLoading = false
            }
            if (isLoading) {
                // Mostrar la pantalla de carga si isLoading es true
                LoadingScreen()
            } else {
                // Mostrar el contenido de la pestaña seleccionada
                when (selectedTabIndex) {
                    0 -> {
                        GoingsMap(goings = goings)
                    }

                    1 -> {
                        ReturnsMaps(returns = returns)
                    }
                }
            }


        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GoingsMap(goings: List<Stop>) {

    var isSheetOpen by remember {
        mutableStateOf(false)
    }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = isSheetOpen)
    var selectedStop by remember { mutableStateOf<Stop?>(null) }

    // Latitud y longitud de Villahermosa, Tabasco
    val villahermosa = LatLng(17.989456, -92.947506)
    val initialPosition = if (goings.isNotEmpty()) {
        val (latitude, longitude) = utmToLatLong(goings[0].latitude, goings[0].altitude, 15)
        LatLng(latitude, longitude)
    } else {
        villahermosa
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 12f)
    }

    val routePoints = goings.map { stop ->
        val (latitude, longitude) = utmToLatLong(stop.latitude, stop.altitude, 15)
        LatLng(latitude, longitude)
    }

    GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
    ) {
        // Agregar marcadores para cada parada
        goings.forEach { stop ->
            val (latitude, longitude) = utmToLatLong(stop.latitude, stop.altitude, 15)
            Marker(
                    state = MarkerState(position = LatLng(latitude, longitude)),
                    title = stop.name,
                    snippet = stop.road,
                    onClick = { marker ->
                        selectedStop = stop
                        isSheetOpen = true
                        true
                    }
            )
        }
        // Dibujar la línea que conecta todas las paradas
        if (routePoints.isNotEmpty()) {
            Polyline(
                    points = routePoints,
                    color = Color.Blue,
                    width = 3f
            )
        }
    }
    if (isSheetOpen) {
        ModalBottomSheet(
                sheetState = bottomSheetState,
                onDismissRequest = {
                    isSheetOpen = false
                    selectedStop = null
                },
        ) {
            BottomSheetContent(selectedStop = selectedStop)
        }
    }
}

@Composable
fun BottomSheetContent(selectedStop: Stop?) {
    // Contenido del BottomSheet
    Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
    ) {
        Text(text = "Detalles de la parada:")
        Spacer(modifier = Modifier.height(8.dp))
        selectedStop?.let { stop ->
            Text(text = "Nombre: ${stop.name}")
            Text(text = "Calle: ${stop.road}")
            Card(
                    colors =CardDefaults.cardColors(
                      containerColor = if (stop.is_terminal) Color.Green else Color.Red,
                    ),
                    modifier = Modifier.padding(top = 8.dp)
            ) {
                Text(
                        text = if (stop.is_terminal) "Terminal" else "No Terminal",
                        color = Color.White,
                        modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReturnsMaps(returns: List<Stop>) {

    var isSheetOpen by remember {
        mutableStateOf(false)
    }
    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = isSheetOpen)
    var selectedStop by remember { mutableStateOf<Stop?>(null) }

    // Latitud y longitud de Villahermosa, Tabasco
    val villahermosa = LatLng(17.989456, -92.947506)
    val initialPosition = if (returns.isNotEmpty()) {
        val (latitude, longitude) = utmToLatLong(returns[0].latitude, returns[0].altitude, 15)
        LatLng(latitude, longitude)
    } else {
        villahermosa
    }

    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(initialPosition, 12f)
    }

    val routePoints = returns.map { stop ->
        val (latitude, longitude) = utmToLatLong(stop.latitude, stop.altitude, 15)
        LatLng(latitude, longitude)
    }

    GoogleMap(
            modifier = Modifier.fillMaxSize(),
            cameraPositionState = cameraPositionState
    ) {
        // Agregar marcadores para cada parada
        returns.forEach { stop ->
            val (latitude, longitude) = utmToLatLong(stop.latitude, stop.altitude, 15)
            Marker(
                    state = MarkerState(position = LatLng(latitude, longitude)),
                    title = stop.name,
                    snippet = stop.road,
                    onClick = { marker ->
                        selectedStop = stop
                        isSheetOpen = true
                        true
                    }
            )
        }
        // Dibujar la línea que conecta todas las paradas
        if (routePoints.isNotEmpty()) {
            Polyline(
                    points = routePoints,
                    color = Color.Blue,
                    width = 3f
            )
        }
    }
    if (isSheetOpen) {
        ModalBottomSheet(
                sheetState = bottomSheetState,
                onDismissRequest = {
                    isSheetOpen = false
                    selectedStop = null
                },
        ) {
            BottomSheetContent(selectedStop = selectedStop)
        }
    }
}



fun utmToLatLong(easting: Double, northing: Double, zoneNumber: Int, isNorthernHemisphere: Boolean = true): Pair<Double, Double> {
    val crsFactory = CRSFactory()
    val utmCRS: CoordinateReferenceSystem = crsFactory.createFromName("EPSG:326$zoneNumber")
    val wgs84CRS: CoordinateReferenceSystem = crsFactory.createFromName("EPSG:4326")

    val projCoordinate = ProjCoordinate(easting, northing)
    val result = ProjCoordinate()
    val transform = org.locationtech.proj4j.BasicCoordinateTransform(utmCRS, wgs84CRS)

    transform.transform(projCoordinate, result)

    return Pair(result.y, result.x)
}

@Composable
fun LoadingScreen() {
    Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
    ) {
        CircularProgressIndicator()
    }
}

