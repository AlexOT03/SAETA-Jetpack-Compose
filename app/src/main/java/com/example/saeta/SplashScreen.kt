package com.example.saeta

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.saeta.TopBar.Screens
import com.example.saeta.ui.theme.primaryLight
import kotlinx.coroutines.delay

@Composable
fun Splash(){
    Column(
        modifier = Modifier
                .fillMaxSize().background(primaryLight),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.ic_launcher_foreground),
                modifier = Modifier.size(250.dp), contentScale = ContentScale.Crop, contentDescription =null )
    }
}

@Composable
fun SplashScreens(navController: NavController){
    LaunchedEffect(key1 = true) {
        delay(1000)
        navController.popBackStack()
        navController.navigate(Screens.HomeScreen.route)
    }
    Splash()
}