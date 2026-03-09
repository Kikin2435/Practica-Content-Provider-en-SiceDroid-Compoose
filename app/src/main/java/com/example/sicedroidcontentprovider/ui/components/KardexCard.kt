package com.example.sicedroidcontentprovider.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.sicedroidcontentprovider.data.Kardex

val TecBlue = Color(0xFF1E5AA8)

@Composable
fun KardexCard(
    kardex: Kardex,
    onDelete: (Kardex) -> Unit,
    onUpdate: (Kardex) -> Unit
) {
    var editing by remember { mutableStateOf(false) }
    var showDeleteDialog by remember { mutableStateOf(false) } // Estado para el diálogo

    var clvMat by remember { mutableStateOf(kardex.ClvMat) }
    var clvOfi by remember { mutableStateOf(kardex.ClvOfiMat) }
    var materia by remember { mutableStateOf(kardex.Materia) }
    var cdts by remember { mutableStateOf(kardex.Cdts.toString()) }
    var calif by remember { mutableStateOf(kardex.Calif.toString()) }
    var acred by remember { mutableStateOf(kardex.Acred) }

    if (showDeleteDialog) {
        AlertDialog(
            onDismissRequest = { showDeleteDialog = false },
            title = { Text("¿Eliminar materia?") },
            text = { Text("Esta acción eliminará '${kardex.Materia}' permanentemente de la base de datos externa.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        val nuevo = Kardex(
                            id = kardex.id,
                            ClvMat = clvMat,
                            ClvOfiMat = clvOfi,
                            Materia = materia,
                            Cdts = cdts.toIntOrNull() ?: 0,
                            Calif = calif.toIntOrNull() ?: 0,
                            Acred = acred
                        )
                        onDelete(nuevo)
                        showDeleteDialog = false
                    },
                    colors = ButtonDefaults.textButtonColors(contentColor = Color.Red)
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text("Cancelar")
                }
            }
        )
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFF7F9FC)),
        elevation = CardDefaults.cardElevation(6.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp).fillMaxWidth()) {
            if (editing) {
                Text("Editar Materia", style = MaterialTheme.typography.titleMedium, color = TecBlue)
                Spacer(modifier = Modifier.height(10.dp))
                OutlinedTextField(clvMat, { clvMat = it }, label = { Text("Clave Materia") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(clvOfi, { clvOfi = it }, label = { Text("Clave Oficial") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(materia, { materia = it }, label = { Text("Materia") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(cdts, { cdts = it }, label = { Text("Créditos") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(calif, { calif = it }, label = { Text("Calificación") }, modifier = Modifier.fillMaxWidth())
                OutlinedTextField(acred, { acred = it }, label = { Text("Acreditación") }, modifier = Modifier.fillMaxWidth())
                Spacer(modifier = Modifier.height(14.dp))
                Button(
                    onClick = {
                        val nuevo = Kardex(
                            id = kardex.id,
                            ClvMat = clvMat,
                            ClvOfiMat = clvOfi,
                            Materia = materia,
                            Cdts = cdts.toIntOrNull() ?: 0,
                            Calif = calif.toIntOrNull() ?: 0,
                            Acred = acred
                        )
                        onUpdate(nuevo)
                        editing = false
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = TecBlue)
                ) {
                    Icon(Icons.Default.Check, null)
                    Spacer(modifier = Modifier.width(6.dp))
                    Text("Guardar")
                }
            } else {
                Text(text = kardex.Materia, style = MaterialTheme.typography.titleLarge, color = TecBlue)
                Spacer(modifier = Modifier.height(10.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                    Column {
                        Text("Créditos", style = MaterialTheme.typography.labelMedium)
                        Text(kardex.Cdts.toString())
                    }
                    Column {
                        Text("Calificación", style = MaterialTheme.typography.labelMedium)
                        Text(text = kardex.Calif.toString(), style = MaterialTheme.typography.titleMedium)
                    }
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                    Button(onClick = { editing = true }, colors = ButtonDefaults.buttonColors(containerColor = TecBlue)) {
                        Icon(Icons.Default.Edit, null)
                        Text(" Editar")
                    }
                    Button(
                        onClick = { showDeleteDialog = true }, // Activa el diálogo
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD32F2F))
                    ) {
                        Icon(Icons.Default.Delete, null)
                        Text(" Eliminar")
                    }
                }
            }
        }
    }
}
