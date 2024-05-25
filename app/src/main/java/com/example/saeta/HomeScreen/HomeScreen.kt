package com.example.saeta.HomeScreen

import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Spinner
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.saeta.API.Data
import com.example.saeta.API.Route
import com.example.saeta.API.ViewModels.RouteViewModel
import com.example.saeta.R
import com.example.saeta.ui.theme.inversePrimaryLight
import com.example.saeta.ui.theme.secondaryLight
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView


@Composable
fun HomeScreen(routeViewModel: RouteViewModel = viewModel()) {
    val routes by routeViewModel.routes.observeAsState(emptyList())
    val error by routeViewModel.error.observeAsState()
    Column(
            modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(Color(0xffF5F5F5))
    ) {

        Carrousel()
        AdBanner()
        Card(
                modifier = Modifier
                        .fillMaxWidth().fillMaxHeight()
                        .padding(top = 20.dp)
                        .clip(RoundedCornerShape(topStart = 25.dp, topEnd = 25.dp)),
                elevation = CardDefaults.cardElevation(2.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Text("Rutas-Cercanas", modifier = Modifier.padding(start = 20.dp, top = 12.dp), color = Color(0xFF21205A),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold
            )
            if (error != null) {

                Text(
                        text = "No se ha podido conectar con la API",
                        color = Color.Red,
                        modifier = Modifier.padding(top=100.dp)
                                .align(Alignment.CenterHorizontally)
                )
            } else if (routes.isEmpty()) {
                CircularProgressIndicator(
                        modifier = Modifier
                                .size(70.dp).align(Alignment.CenterHorizontally)
                                .padding(top=100.dp),
                        color = secondaryLight,
                        strokeWidth = 6.dp,

                )
            }else{
                RoutesList(routes = routes)
            }
        }

    }
}

@Composable
fun RoutesList(routes: List<Route>){
    if (routes.isEmpty()) {
        Text(
                text = "No routes available",
                modifier = Modifier.padding(16.dp)
        )
    } else {
        LazyColumn(modifier = Modifier.padding(10.dp)) {
            items(routes) { route ->
                RouteItem(route)
            }
        }
    }
}

@Composable
fun RouteItem(route: Route) {
    Card(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, bottom = 10.dp, start = 5.dp, end = 5.dp),
            colors = CardDefaults.cardColors(containerColor = secondaryLight),
            elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Row {
            Image(painter = painterResource(id = R.drawable.icon_route),
                    contentDescription = null,
                    modifier = Modifier
                            .padding(10.dp)
                            .size(50.dp),
                    colorFilter = ColorFilter.tint(inversePrimaryLight)
            )
            Column(
                    modifier = Modifier
                            .padding(10.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.Start
            ) {
                Text(route.long_name , fontSize = 19.2.sp, color = Color.White)
                Text(route.route_type, fontSize = 16.8.sp, color = Color.White)
            }
        }
    }
}

@Composable
fun AdBanner() {
    Box(modifier = Modifier
            .fillMaxWidth()
            .height(60.dp)
            .padding(start = 10.dp, end = 10.dp)
    ) {
        AndroidView(factory = { context ->
            AdView(context).apply {
                setAdSize(AdSize.BANNER)
                adUnitId = "ca-app-pub-3940256099942544/6300978111"
                layoutParams = ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                )
                loadAd(AdRequest.Builder().build())
            }
        },
                update = { adView ->
                    adView.loadAd(AdRequest.Builder().build())
                })
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Carrousel() {
    val images = listOf(
            R.drawable.ic_launcher,
            R.drawable.ic_launcher,

            )
    val statePaper = rememberPagerState(pageCount = { images.size })
    Box(
            modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .height(170.dp)
    ) {
        HorizontalPager(state = statePaper, modifier = Modifier
                .wrapContentSize()
        ) { page ->
            Card(
                    modifier = Modifier
                            .wrapContentSize(),

                    ) {
                Image(painter = painterResource(id = images[page]), contentDescription = null, modifier = Modifier.fillMaxWidth())
            }
        }
        PageIndicator(pageCount = images.size, currentPage = statePaper.currentPage, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 4.dp))

    }
}

@Composable
fun PageIndicator(pageCount: Int, currentPage: Int, modifier: Modifier) {
    Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
    ) {
        repeat(pageCount) {
            IndicatorDots(isSelected = it == currentPage, modifier = modifier)
        }
    }
}

@Composable
fun IndicatorDots(isSelected: Boolean, modifier: Modifier) {
    val size = animateDpAsState(targetValue = if (isSelected) 12.dp else 10.dp, label = "")
    Box(modifier = modifier
            .padding(2.dp)
            .size(size.value)
            .clip(CircleShape)
            .background(if (isSelected) Color(0xff373737) else Color(0xA8373737))
    ) {

    }
}

