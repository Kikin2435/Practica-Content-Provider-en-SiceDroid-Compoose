package com.example.sicedroidcontentprovider.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sicedroidcontentprovider.data.CargaAcademica

@Composable
fun CargaCard(

    carga: CargaAcademica,
    onDelete: () -> Unit,
    onUpdate: (CargaAcademica) -> Unit

) {

    var editando by remember { mutableStateOf(false) }

    var materia by remember { mutableStateOf(carga.Materia) }
    var docente by remember { mutableStateOf(carga.Docente) }
    var grupo by remember { mutableStateOf(carga.Grupo) }
    var creditos by remember { mutableStateOf(carga.CreditosMateria.toString()) }
    var estado by remember { mutableStateOf(carga.EstadoMateria) }

    var lunes by remember { mutableStateOf(carga.Lunes) }
    var martes by remember { mutableStateOf(carga.Martes) }
    var miercoles by remember { mutableStateOf(carga.Miercoles) }
    var jueves by remember { mutableStateOf(carga.Jueves) }
    var viernes by remember { mutableStateOf(carga.Viernes) }
    var sabado by remember { mutableStateOf(carga.Sabado) }

    var observaciones by remember { mutableStateOf(carga.Observaciones) }
    var semipresencial by remember { mutableStateOf(carga.Semipresencial) }
    var clvOficial by remember { mutableStateOf(carga.clvOficial) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            if (!editando) {

                Text("Materia: $materia")
                Text("Docente: $docente")
                Text("Grupo: $grupo")
                Text("Créditos: $creditos")
                Text("Estado: $estado")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Lunes: $lunes")
                Text("Martes: $martes")
                Text("Miércoles: $miercoles")
                Text("Jueves: $jueves")
                Text("Viernes: $viernes")
                Text("Sábado: $sabado")

                Spacer(modifier = Modifier.height(8.dp))

                Text("Observaciones: $observaciones")
                Text("Semipresencial: $semipresencial")
                Text("Clave Oficial: $clvOficial")

            } else {

                OutlinedTextField(
                    value = materia,
                    onValueChange = { materia = it },
                    label = { Text("Materia") }
                )

                OutlinedTextField(
                    value = docente,
                    onValueChange = { docente = it },
                    label = { Text("Docente") }
                )

                OutlinedTextField(
                    value = grupo,
                    onValueChange = { grupo = it },
                    label = { Text("Grupo") }
                )

                OutlinedTextField(
                    value = creditos,
                    onValueChange = { creditos = it },
                    label = { Text("Créditos") }
                )

                OutlinedTextField(
                    value = estado,
                    onValueChange = { estado = it },
                    label = { Text("Estado") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text("Horario")

                OutlinedTextField(
                    value = lunes,
                    onValueChange = { lunes = it },
                    label = { Text("Lunes") }
                )

                OutlinedTextField(
                    value = martes,
                    onValueChange = { martes = it },
                    label = { Text("Martes") }
                )

                OutlinedTextField(
                    value = miercoles,
                    onValueChange = { miercoles = it },
                    label = { Text("Miércoles") }
                )

                OutlinedTextField(
                    value = jueves,
                    onValueChange = { jueves = it },
                    label = { Text("Jueves") }
                )

                OutlinedTextField(
                    value = viernes,
                    onValueChange = { viernes = it },
                    label = { Text("Viernes") }
                )

                OutlinedTextField(
                    value = sabado,
                    onValueChange = { sabado = it },
                    label = { Text("Sábado") }
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = observaciones,
                    onValueChange = { observaciones = it },
                    label = { Text("Observaciones") }
                )

                OutlinedTextField(
                    value = semipresencial,
                    onValueChange = { semipresencial = it },
                    label = { Text("Semipresencial") }
                )

                OutlinedTextField(
                    value = clvOficial,
                    onValueChange = { clvOficial = it },
                    label = { Text("Clave Oficial") }
                )

            }

            Spacer(modifier = Modifier.height(10.dp))

            Row {

                Button(

                    onClick = {

                        if (editando) {

                            onUpdate(

                                carga.copy(

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

                            )

                        }

                        editando = !editando

                    }

                ) {

                    Text(if (editando) "Guardar" else "Modificar")

                }

                Spacer(modifier = Modifier.width(10.dp))

                Button(
                    onClick = { onDelete() }
                ) {
                    Text("Eliminar")
                }

            }

        }

    }

}