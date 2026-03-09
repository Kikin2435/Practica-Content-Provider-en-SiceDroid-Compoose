package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.sicedroidcontentprovider.ui.ViewModel.ContentViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    viewModel: ContentViewModel = viewModel()
) {
    var matricula by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val azulTecNM = Color(0xFF1B396A)
    val context = LocalContext.current

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
            .background(fondo),
        contentAlignment = Alignment.Center
    ) {
        Card(
            shape = RoundedCornerShape(20.dp),
            elevation = CardDefaults.cardElevation(12.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(
                modifier = Modifier
                    .padding(30.dp)
                    .width(320.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = "SICEDROID", fontSize = 28.sp, color = azulTecNM)
                Spacer(modifier = Modifier.height(5.dp))
                Text(text = "Tecnológico Nacional de México", color = Color.Gray, fontSize = 13.sp)
                
                Spacer(modifier = Modifier.height(25.dp))

                // Campo Matrícula
                OutlinedTextField(
                    value = matricula,
                    onValueChange = { matricula = it },
                    label = { Text("Matrícula") },
                    leadingIcon = { Icon(Icons.Default.Person, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // Campo Password
                OutlinedTextField(
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                    visualTransformation = PasswordVisualTransformation(),
                    leadingIcon = { Icon(Icons.Default.Lock, contentDescription = null) },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true
                )

                // Mensaje de Error
                viewModel.loginError?.let { error ->
                    Spacer(modifier = Modifier.height(10.dp))
                    Text(text = error, color = Color.Red, fontSize = 12.sp)
                }

                Spacer(modifier = Modifier.height(25.dp))

                Button(
                    onClick = { 
                        viewModel.verificarSesionIniciada(context, matricula, password) {
                            onLoginSuccess()
                        }
                    },
                    modifier = Modifier.fillMaxWidth().height(50.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = azulTecNM)
                ) {
                    Text("Iniciar sesión", fontSize = 16.sp)
                }
            }
        }
    }
}
