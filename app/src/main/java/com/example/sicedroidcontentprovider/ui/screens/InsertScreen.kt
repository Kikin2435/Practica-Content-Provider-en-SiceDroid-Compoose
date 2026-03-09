package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sicedroidcontentprovider.data.Kardex
import com.example.sicedroidcontentprovider.ui.ViewModel.ContentViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(

    navController: NavController,
    viewModel: ContentViewModel = viewModel()

) {

    val azulTecNM = Color(0xFF003366)

    val context = LocalContext.current


    var clvMat by remember { mutableStateOf("") }
    var clvOfi by remember { mutableStateOf("") }
    var materia by remember { mutableStateOf("") }
    var cdts by remember { mutableStateOf("") }
    var calif by remember { mutableStateOf("") }
    var acred by remember { mutableStateOf("") }

    Scaffold(

        containerColor = Color(0xFFF2F4F8),

        topBar = {

            TopAppBar(

                title = {

                    Row {
                        Icon(
                            imageVector = Icons.Default.AccountBox,
                            contentDescription = null,
                            tint = Color.White
                        )

                        Spacer(modifier = Modifier.width(8.dp))

                        Text(
                            "Agregar Kardex",
                            color = Color.White
                        )
                    }

                },

                navigationIcon = {

                    IconButton(
                        onClick = { navController.popBackStack() }
                    ) {

                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )

                    }

                },

                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = azulTecNM
                )

            )

        }

    ) { padding ->

        Column(

            modifier = Modifier
                .padding(padding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp)
                .fillMaxSize(),

            verticalArrangement = Arrangement.spacedBy(14.dp)

        ) {

            Text(
                "Información de la materia",
                style = MaterialTheme.typography.titleMedium
            )

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

                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically

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

                        viewModel.insertarKardex(context, nuevo)

                        navController.popBackStack()

                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = azulTecNM
                    )

                ) {

                    Icon(
                        imageVector = Icons.Default.Check,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text("Guardar")

                }

            }

        }

    }

}