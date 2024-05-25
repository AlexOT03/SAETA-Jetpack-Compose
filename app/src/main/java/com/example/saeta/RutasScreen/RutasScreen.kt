package com.example.saeta.RutasScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.saeta.Components.SearchText
import com.example.saeta.R

@Composable
fun RutasScreen() {
    val textValue = remember {
        mutableStateOf("")
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
        Box(modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .clickable { }) {
            Row {
                Icon(painter = painterResource(id = R.drawable.icon_route), contentDescription = null, Modifier.size(70.dp))
                Column(
                        modifier = Modifier.padding(10.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.Start,
                ) {
                    Text("Ruta 1")
                    Text("Ver Horarios")
                }
            }
        }
    }
}