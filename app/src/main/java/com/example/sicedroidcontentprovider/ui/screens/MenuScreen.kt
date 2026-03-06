package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun MenuScreen(

    onCargaClick: () -> Unit,
    onKardexClick: () -> Unit

) {

    val azulTecNM = Color(0xFF1B396A)

    val fondo = Brush.verticalGradient(
        colors = listOf(
            azulTecNM,
            Color(0xFF274B87),
            Color(0xFF3A5F9E)
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(fondo)
    ) {

        Column(

            modifier = Modifier
                .fillMaxSize()
                .padding(30.dp),

            horizontalAlignment = Alignment.CenterHorizontally

        ) {

            Spacer(modifier = Modifier.height(40.dp))

            Text(
                text = "Menú Principal",
                fontSize = 28.sp,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(40.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clickable { onCargaClick() },

                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.AccountBox,
                        contentDescription = "Carga",
                        tint = azulTecNM,
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Column {

                        Text(
                            text = "Carga Académica",
                            fontSize = 20.sp
                        )

                        Text(
                            text = "Consulta tus materias",
                            color = Color.Gray
                        )

                    }

                }

            }

            Spacer(modifier = Modifier.height(25.dp))

            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .clickable { onKardexClick() },

                shape = RoundedCornerShape(20.dp),
                elevation = CardDefaults.cardElevation(10.dp)
            ) {

                Row(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(20.dp),

                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Icon(
                        Icons.Default.List,
                        contentDescription = "Kardex",
                        tint = azulTecNM,
                        modifier = Modifier.size(50.dp)
                    )

                    Spacer(modifier = Modifier.width(20.dp))

                    Column {

                        Text(
                            text = "Kardex",
                            fontSize = 20.sp
                        )

                        Text(
                            text = "Historial académico",
                            color = Color.Gray
                        )

                    }

                }

            }

        }

    }

}