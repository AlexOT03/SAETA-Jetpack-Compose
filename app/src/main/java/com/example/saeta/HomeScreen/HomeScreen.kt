package com.example.saeta.HomeScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.saeta.R

@Composable
fun HomeScreen(){
    val textValue= remember {
        mutableStateOf("")
    }
    Column(
            modifier = Modifier.padding(end=20.dp, start = 20.dp, top=10.dp)
    ){
        OutlinedTextField(
                label = { Text(text = "Buscar Rutas") },
                value = textValue.value,
                onValueChange ={
                    textValue.value=it
                },
                leadingIcon = {
                              Icon(imageVector = Icons.Default.Search, contentDescription = null)
                },
                keyboardOptions = KeyboardOptions.Default,
                modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Box(
                modifier = Modifier
                        .background(Color(0xFF8C0009))
                        .fillMaxWidth(),
                contentAlignment = Alignment.Center


        ){
            Text(text = "Rutas Tabasco", color = Color.White, fontSize = 32.sp, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.height(20.dp))
        }
        Divider(color = Color.White, modifier = Modifier.height(2.dp).width(50.dp))
        Box(
                modifier = Modifier
                        .background(Color(0xFF8C0009))
                        .fillMaxWidth(),
                contentAlignment = Alignment.Center
        ){
            Text(text="hola")
        }
    }
}

