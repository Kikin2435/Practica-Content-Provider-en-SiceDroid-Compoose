package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.sicedroidcontentprovider.data.Kardex

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(

    navController: NavController,
    onGuardar: (Kardex) -> Unit

) {

    var clvMat by remember { mutableStateOf("") }
    var clvOfi by remember { mutableStateOf("") }
    var materia by remember { mutableStateOf("") }
    var cdts by remember { mutableStateOf("") }
    var calif by remember { mutableStateOf("") }
    var acred by remember { mutableStateOf("") }

    Scaffold(

        topBar = {

            TopAppBar(

                title = { Text("Agregar Kardex") },

                navigationIcon = {

                    IconButton(onClick = {

                        navController.popBackStack()

                    }) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )

                    }

                }

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {

            OutlinedTextField(
                value = clvMat,
                onValueChange = { clvMat = it },
                label = { Text("Clave Materia") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = clvOfi,
                onValueChange = { clvOfi = it },
                label = { Text("Clave Oficial") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = materia,
                onValueChange = { materia = it },
                label = { Text("Materia") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = cdts,
                onValueChange = { cdts = it },
                label = { Text("Créditos") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = calif,
                onValueChange = { calif = it },
                label = { Text("Calificación") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = acred,
                onValueChange = { acred = it },
                label = { Text("Acreditación") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Row(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                Button(

                    onClick = {

                        val nuevo = Kardex(

                            ClvMat = clvMat,
                            ClvOfiMat = clvOfi,
                            Materia = materia,
                            Cdts = cdts.toIntOrNull() ?: 0,
                            Calif = calif.toIntOrNull() ?: 0,
                            Acred = acred

                        )

                        onGuardar(nuevo)

                    }

                ) {
                    Text("Guardar")
                }

                Button(

                    onClick = {

                        navController.popBackStack()

                    }

                ) {
                    Text("Cancelar")
                }

            }

        }

    }

}