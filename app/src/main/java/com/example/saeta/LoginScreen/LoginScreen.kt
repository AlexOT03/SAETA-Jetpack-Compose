package com.example.saeta.LoginScreen

import android.nfc.Tag
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.saeta.TopBar.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(navController: NavController) {
    val user = remember {
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")

    }
    Column(
            modifier = Modifier
                    .fillMaxSize()
                    .padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Bienvenido de Vuelta",
                fontSize = 32.sp,
                fontWeight = FontWeight.SemiBold
                )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
                value = user.value,
                onValueChange = { user.value = it },
                label = { Text("Usuario", modifier=Modifier.background(Color.Transparent), color = Color.Black) },
                placeholder = { Text("Usuario", color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color(0xFF8C0009),
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White
                ),
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
                value = password.value,
                onValueChange = { password.value = it },
                label = { Text("Contraseña",modifier=Modifier.background(Color.Transparent), color = Color.Black) },
                placeholder = { Text("Contraseña", color = Color.Gray) },
                colors = TextFieldDefaults.colors(
                        cursorColor = Color.Black,
                        disabledLabelColor = Color.LightGray,
                        unfocusedIndicatorColor = Color.Black,
                        focusedIndicatorColor = Color(0xFF8C0009),
                        unfocusedContainerColor = Color.White,
                        focusedContainerColor = Color.White

                        ),
                modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { navController.navigate(Screens.NavigationDrawer.route)  },
        modifier = Modifier.fillMaxWidth(),
                colors= ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF8C0009)
                )
        ) {
            Text("Iniciar Sesión")
        }

    }
}

