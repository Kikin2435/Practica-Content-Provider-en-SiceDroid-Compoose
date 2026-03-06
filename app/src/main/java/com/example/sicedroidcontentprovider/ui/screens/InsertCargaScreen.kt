package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sicedroidcontentprovider.data.CargaAcademica
import androidx.navigation.NavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertCargaScreen(
    navController: NavController,
    onGuardar: (CargaAcademica) -> Unit

) {

    var materia by remember { mutableStateOf("") }
    var docente by remember { mutableStateOf("") }
    var grupo by remember { mutableStateOf("") }
    var creditos by remember { mutableStateOf("") }
    var estado by remember { mutableStateOf("") }

    var lunes by remember { mutableStateOf("") }
    var martes by remember { mutableStateOf("") }
    var miercoles by remember { mutableStateOf("") }
    var jueves by remember { mutableStateOf("") }
    var viernes by remember { mutableStateOf("") }
    var sabado by remember { mutableStateOf("") }

    var observaciones by remember { mutableStateOf("") }
    var semipresencial by remember { mutableStateOf("") }
    var clvOficial by remember { mutableStateOf("") }

    Scaffold(

        topBar = {

            CenterAlignedTopAppBar(

                title = { Text("Agregar Materia") },

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
                .padding(16.dp),

            verticalArrangement = Arrangement.spacedBy(10.dp)

        ) {

            OutlinedTextField(
                value = materia,
                onValueChange = { materia = it },
                label = { Text("Materia") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = docente,
                onValueChange = { docente = it },
                label = { Text("Docente") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = grupo,
                onValueChange = { grupo = it },
                label = { Text("Grupo") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = creditos,
                onValueChange = { creditos = it },
                label = { Text("Créditos") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = estado,
                onValueChange = { estado = it },
                label = { Text("Estado de Materia") },
                modifier = Modifier.fillMaxWidth()
            )

            Divider()

            Text("Horario", style = MaterialTheme.typography.titleMedium)

            OutlinedTextField(
                value = lunes,
                onValueChange = { lunes = it },
                label = { Text("Lunes") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = martes,
                onValueChange = { martes = it },
                label = { Text("Martes") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = miercoles,
                onValueChange = { miercoles = it },
                label = { Text("Miércoles") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = jueves,
                onValueChange = { jueves = it },
                label = { Text("Jueves") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = viernes,
                onValueChange = { viernes = it },
                label = { Text("Viernes") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = sabado,
                onValueChange = { sabado = it },
                label = { Text("Sábado") },
                modifier = Modifier.fillMaxWidth()
            )

            Divider()

            OutlinedTextField(
                value = observaciones,
                onValueChange = { observaciones = it },
                label = { Text("Observaciones") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = semipresencial,
                onValueChange = { semipresencial = it },
                label = { Text("Semipresencial") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = clvOficial,
                onValueChange = { clvOficial = it },
                label = { Text("Clave Oficial") },
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(20.dp))

            Button(

                onClick = {

                    val nuevaCarga = CargaAcademica(

                        Materia = materia,
                        Docente = docente,
                        Grupo = grupo,
                        CreditosMateria = creditos.toIntOrNull() ?: 0,
                        EstadoMateria = estado,

                        Lunes = lunes,
                        Martes = martes,
                        Miercoles = miercoles,
                        Jueves = jueves,
                        Viernes = viernes,
                        Sabado = sabado,

                        Observaciones = observaciones,
                        Semipresencial = semipresencial,
                        clvOficial = clvOficial

                    )

                    onGuardar(nuevaCarga)

                },

                modifier = Modifier.fillMaxWidth()

            ) {

                Text("Guardar")

            }

            Spacer(modifier = Modifier.height(40.dp))

        }

    }

}