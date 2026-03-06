package com.example.sicedroidcontentprovider.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Check
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
    onDelete: () -> Unit,
    onUpdate: (Kardex) -> Unit

) {

    var editing by remember { mutableStateOf(false) }

    var clvMat by remember { mutableStateOf(kardex.ClvMat) }
    var clvOfi by remember { mutableStateOf(kardex.ClvOfiMat) }
    var materia by remember { mutableStateOf(kardex.Materia) }
    var cdts by remember { mutableStateOf(kardex.Cdts.toString()) }
    var calif by remember { mutableStateOf(kardex.Calif.toString()) }
    var acred by remember { mutableStateOf(kardex.Acred) }

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp, vertical = 8.dp),

        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFF7F9FC)
        ),

        elevation = CardDefaults.cardElevation(6.dp)

    ) {

        Column(

            modifier = Modifier
                .padding(18.dp)
                .fillMaxWidth()

        ) {

            if (editing) {

                Text(
                    text = "Editar Materia",
                    style = MaterialTheme.typography.titleMedium,
                    color = TecBlue
                )

                Spacer(modifier = Modifier.height(10.dp))

                OutlinedTextField(clvMat,{clvMat=it},label={Text("Clave Materia")},modifier=Modifier.fillMaxWidth())
                OutlinedTextField(clvOfi,{clvOfi=it},label={Text("Clave Oficial")},modifier=Modifier.fillMaxWidth())
                OutlinedTextField(materia,{materia=it},label={Text("Materia")},modifier=Modifier.fillMaxWidth())
                OutlinedTextField(cdts,{cdts=it},label={Text("Créditos")},modifier=Modifier.fillMaxWidth())
                OutlinedTextField(calif,{calif=it},label={Text("Calificación")},modifier=Modifier.fillMaxWidth())
                OutlinedTextField(acred,{acred=it},label={Text("Acreditación")},modifier=Modifier.fillMaxWidth())

                Spacer(modifier = Modifier.height(14.dp))

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

                        onUpdate(nuevo)
                        editing = false

                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = TecBlue
                    )

                ) {

                    Icon(Icons.Default.Check,null)

                    Spacer(modifier = Modifier.width(6.dp))

                    Text("Guardar")

                }

            } else {

                Text(
                    text = kardex.Materia,
                    style = MaterialTheme.typography.titleLarge,
                    color = TecBlue
                )

                Spacer(modifier = Modifier.height(4.dp))

                Divider(color = TecBlue.copy(alpha = 0.2f))

                Spacer(modifier = Modifier.height(10.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {

                        Text("Clave Materia", style = MaterialTheme.typography.labelMedium)
                        Text(kardex.ClvMat)

                    }

                    Column {

                        Text("Clave Oficial", style = MaterialTheme.typography.labelMedium)
                        Text(kardex.ClvOfiMat)

                    }

                }

                Spacer(modifier = Modifier.height(8.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Column {

                        Text("Créditos", style = MaterialTheme.typography.labelMedium)
                        Text(kardex.Cdts.toString())

                    }

                    Column {

                        Text("Calificación", style = MaterialTheme.typography.labelMedium)

                        val colorCalif =
                            if (kardex.Calif >= 90) Color(0xFF2E7D32)
                            else if (kardex.Calif >= 70) Color(0xFFF9A825)
                            else Color(0xFFC62828)

                        Text(
                            text = kardex.Calif.toString(),
                            color = colorCalif,
                            style = MaterialTheme.typography.titleMedium
                        )

                    }

                }

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Acreditación: ${kardex.Acred}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TecBlue
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(

                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalAlignment = Alignment.CenterVertically

                ) {

                    Button(

                        onClick = { editing = true },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = TecBlue
                        )

                    ) {

                        Icon(Icons.Default.Edit,null)

                        Spacer(modifier = Modifier.width(5.dp))

                        Text("Editar")

                    }

                    Button(

                        onClick = { onDelete() },

                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFD32F2F)
                        )

                    ) {

                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = null
                        )

                        Spacer(modifier = Modifier.width(5.dp))

                        Text("Eliminar")

                    }

                }

            }

        }

    }

}