package com.example.saeta.TopBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saeta.R
import com.example.saeta.ui.theme.primaryLight
import kotlinx.coroutines.launch


@Composable
fun NavBarHeader() {
    Row(
            modifier = Modifier
                    .fillMaxWidth()
                    .background(primaryLight)
                    .wrapContentHeight()
                    .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,

            ) {
        Image(painter = painterResource(id = R.drawable.img), contentDescription = null,
                modifier = Modifier
                        .size(110.dp)
                        .padding(start = 5.dp, top = 20.dp, bottom = 20.dp)
        )
        Column {
            Text(text = "RUTAS",
                    modifier = Modifier
                            .padding(end = 20.dp),

                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
            )
            Text(text = "TABASCO",
                    modifier = Modifier
                            .padding(end = 20.dp),

                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopApp(drawerState: DrawerState) {
    val scope = rememberCoroutineScope()
    TopAppBar(
            title = { Text(text = "") },
            navigationIcon = {
                IconButton(onClick = {
                    scope.launch {
                        drawerState.open()
                    }
                }) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = null)
                }
            })
}