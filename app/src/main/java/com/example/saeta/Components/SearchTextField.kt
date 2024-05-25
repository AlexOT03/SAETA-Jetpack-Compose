package com.example.saeta.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.saeta.ui.theme.primaryLight
import com.example.saeta.ui.theme.secondaryLight

@Composable
fun SearchText(textValue:MutableState<String>){

    OutlinedTextField(
            label = { Text(text = "Buscar Rutas", modifier= Modifier.background(Color.Transparent)) },
            value = textValue.value,
            onValueChange ={
                textValue.value=it
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search , contentDescription = null)
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default,
            modifier = Modifier
                    .fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = primaryLight,
                    unfocusedBorderColor = secondaryLight,
                    focusedLabelColor = primaryLight
            )
    )
}