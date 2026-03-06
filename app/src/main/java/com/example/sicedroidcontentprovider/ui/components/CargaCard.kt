package com.example.sicedroidcontentprovider.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import com.example.sicedroidcontentprovider.data.CargaAcademica

@Composable
fun CargaCard(
    carga: CargaAcademica,
    onDelete: () -> Unit,
    onUpdate: (CargaAcademica) -> Unit
) {

    val azulTec = Color(0xFF3F6FB6)
    val rojoEliminar = Color(0xFFD32F2F)

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
            .padding(horizontal = 12.dp, vertical = 8.dp),
        shape = RoundedCornerShape(18.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            if (!editando) {

                Text(
                    text = materia,
                    fontSize = 22.sp,
                    color = azulTec
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text("Docente: $docente")
                Text("Grupo: $grupo")
                Text("Créditos: $creditos")
                Text("Estado: $estado")

                Spacer(modifier = Modifier.height(10.dp))

                Divider()

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "Horario",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(6.dp))

                Column {
                    Text("Lunes: $lunes")
                    Text("Martes: $martes")
                    Text("Miércoles: $miercoles")
                    Text("Jueves: $jueves")
                    Text("Viernes: $viernes")
                    Text("Sábado: $sabado")
                }

                Spacer(modifier = Modifier.height(10.dp))

                Divider()

                Spacer(modifier = Modifier.height(10.dp))

                Text("Observaciones: $observaciones")
                Text("Semipresencial: $semipresencial")
                Text("Clave Oficial: $clvOficial")

            } else {

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
                    label = { Text("Estado") },
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text("Horario")

                OutlinedTextField(lunes, { lunes = it }, label = { Text("Lunes") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(martes, { martes = it }, label = { Text("Martes") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(miercoles, { miercoles = it }, label = { Text("Miércoles") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(jueves, { jueves = it }, label = { Text("Jueves") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(viernes, { viernes = it }, label = { Text("Viernes") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(sabado, { sabado = it }, label = { Text("Sábado") }, modifier = Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(observaciones, { observaciones = it }, label = { Text("Observaciones") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(semipresencial, { semipresencial = it }, label = { Text("Semipresencial") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(clvOficial, { clvOficial = it }, label = { Text("Clave Oficial") }, modifier = Modifier.fillMaxWidth())
            }

            Spacer(modifier = Modifier.height(14.dp))

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

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
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = azulTec
                    )
                ) {

                    Icon(
                        imageVector = if (editando) Icons.Default.Check else Icons.Default.Edit,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text(if (editando) "Guardar" else "Editar")
                }

                Button(
                    onClick = { onDelete() },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = rojoEliminar
                    )
                ) {

                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = null
                    )

                    Spacer(modifier = Modifier.width(6.dp))

                    Text("Eliminar")
                }
            }
        }
    }
}