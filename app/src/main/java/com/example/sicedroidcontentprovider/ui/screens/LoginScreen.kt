package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit
) {

    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Card {

            Column(
                modifier = Modifier
                    .padding(30.dp)
                    .width(300.dp)
            ) {

                Text(
                    text = "SICEDROID",
                    style = MaterialTheme.typography.headlineMedium
                )

                Spacer(modifier = Modifier.height(20.dp))

                OutlinedTextField(
                    value = usuario,
                    onValueChange = { usuario = it },
                    label = { Text("Usuario") }
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") }
                )

                Spacer(modifier = Modifier.height(20.dp))

                Button(
                    onClick = { onLoginSuccess() },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text("Iniciar sesión")
                }

            }

        }

    }

}