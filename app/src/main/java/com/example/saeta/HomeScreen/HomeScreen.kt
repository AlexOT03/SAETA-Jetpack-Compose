package com.example.saeta.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saeta.R
import com.example.saeta.ui.theme.primaryLight
import com.example.saeta.ui.theme.secondaryLight

@Composable
fun HomeScreen(){
    val textValue= remember {
        mutableStateOf("")
    }
    Column(
            modifier = Modifier.padding(end=20.dp, start = 20.dp)
    ){
        OutlinedTextField(
                label = { Text(text = "Buscar Rutas", modifier=Modifier.background(Color.Transparent)) },
                value = textValue.value,
                onValueChange ={
                    textValue.value=it
                },
                leadingIcon = {
                              Icon(imageVector = Icons.Default.Search , contentDescription = null)
                },
                keyboardOptions = KeyboardOptions.Default,
                modifier = Modifier
                        .fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = primaryLight,
                        unfocusedBorderColor = secondaryLight,
                        focusedLabelColor = primaryLight
                )
        )
        Spacer(modifier = Modifier.height(10.dp))
        Box(
                modifier = Modifier
                        .background(primaryLight)
                        .fillMaxWidth()
                        .padding(10.dp),
                contentAlignment = Alignment.Center


        ){
            Text(text = "Rutas Tabasco", color = Color.White, fontSize = 32.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
        }
        Divider(color = Color.White, modifier = Modifier
                .height(1.dp)
                .fillMaxWidth())
        Box(
                modifier = Modifier
                        .background(primaryLight)
                        .fillMaxWidth()
                        .padding(10.dp),
                contentAlignment = Alignment.Center
        ){
            Row(
                    modifier = Modifier.padding(10.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
            ) {
                Image(painter = painterResource(id = R.drawable.img), contentDescription =null, modifier=Modifier.size(50.dp) )
                Image(painter = painterResource(id = R.drawable.icon_route), contentDescription =null, modifier=Modifier.size(50.dp), colorFilter = ColorFilter.tint(Color.White))
            }
        }
    }
}

