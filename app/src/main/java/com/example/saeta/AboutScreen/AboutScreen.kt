package com.example.saeta.AboutScreen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import com.example.saeta.R

@Composable
fun AboutScreen() {
    val modifier = Modifier
    LazyColumn(
            modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
    ) {
        item {
            Image(painter = painterResource(id = R.drawable.background_saeta), contentDescription = null,
                    modifier
                            .fillMaxWidth()
                            .height(149.dp)
            )
            Spacer(modifier.height(12.dp))

            Image(painter = painterResource(id = R.drawable.ic_launcher_foreground), contentDescription = null,
                    modifier
                            .size(150.dp),
                    alignment = Alignment.Center
            )
            Spacer(modifier.height(12.dp))

            Text("Bienvenido a Sistema de Autotransporte y Enlace de Tabasco",
                    textAlign = TextAlign.Center
            )

            Spacer(modifier.height(15.dp))

            Text("SAETA Villahermosa",
                    modifier.padding(bottom = 20.dp, start = 20.dp, end = 20.dp),
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
            )

            Text("En esta aplicación encontrarás información del transporte publico de Villahermosa",
                    modifier
                            .padding(20.dp),
                    textAlign = TextAlign.Center
            )

            Spacer(modifier.height(5.dp))

            Divider(
                    modifier
                            .fillMaxWidth()
                            .height(1.dp)
            )

            Spacer(modifier.height(10.dp))

            Text("Contacta con nosotros")

            val context = LocalContext.current
            val facebookUrl = "https://www.facebook.com/profile.php?id=100066940217670"

            TextButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookUrl))
                        context.startActivity(intent)
                    },
                    modifier
                            .fillMaxWidth()
                            .clip(RectangleShape)
                            .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),

                    ) {
                Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_facebook), contentDescription = null,
                            tint = Color.Blue
                    )
                    Text("Me gusta en facebook",
                            textAlign = TextAlign.Center,
                            color = Color.Blue
                    )
                }
            }
            Divider(
                    modifier
                            .fillMaxWidth()
                            .height(1.dp)
            )
            TextButton(
                    onClick = {
                        Toast.makeText(
                                context, "No disponible 😥",
                                Toast.LENGTH_LONG).show();
                    },
                    modifier
                            .fillMaxWidth()
                            .clip(RectangleShape)
                            .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),

                    ) {
                Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_mail), contentDescription = null,
                            tint = Color.Blue
                    )
                    Text("Contacta con nosotros",
                            textAlign = TextAlign.Center,
                            color = Color.Blue
                    )
                }
            }
            Divider(
                    modifier
                            .fillMaxWidth()
                            .height(1.dp)
            )
            val webPageUrl = "https://saetavsa.blogspot.com/?fbclid=IwZXh0bgNhZW0CMTAAAR1Sz8ULJWAbaserjkfk-6UjwHoeRtmE7PuEXMnXHYlvqHjoxyP6-1HhQ6U_aem_AU1GEOYLvVJTDhg4PjCpmMs3lrqXmeok9nbblNzlog18FyCQbErXLpdECY_6WW9eVMNndubIe_D2N28UwEzZm0tw"
            TextButton(
                    onClick = {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(webPageUrl))
                        context.startActivity(intent)
                    },
                    modifier
                            .fillMaxWidth()
                            .clip(RectangleShape)
                            .padding(top = 5.dp, start = 20.dp, end = 20.dp, bottom = 5.dp),

                    ) {
                Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_blogger), contentDescription = null,
                            tint = Color.Blue
                    )
                    Text("Visita nuestra pagina",
                            textAlign = TextAlign.Center,
                            color = Color.Blue
                    )
                }
            }
            Divider(
                    modifier
                            .fillMaxWidth()
                            .height(1.dp)
            )
            Text("Version 0.0.1")
            Spacer(modifier.height(5.dp))


        }
    }

}
