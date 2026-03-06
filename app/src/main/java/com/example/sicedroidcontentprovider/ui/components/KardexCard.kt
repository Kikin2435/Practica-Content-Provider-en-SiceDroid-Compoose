package com.example.sicedroidcontentprovider.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sicedroidcontentprovider.data.Kardex

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
            .padding(10.dp)
    ) {

        Column(
            modifier = Modifier.padding(16.dp)
        ) {

            if (editing) {

                OutlinedTextField(clvMat,{clvMat=it},label={Text("Clave Materia")})
                OutlinedTextField(clvOfi,{clvOfi=it},label={Text("Clave Oficial")})
                OutlinedTextField(materia,{materia=it},label={Text("Materia")})
                OutlinedTextField(cdts,{cdts=it},label={Text("Creditos")})
                OutlinedTextField(calif,{calif=it},label={Text("Calificación")})
                OutlinedTextField(acred,{acred=it},label={Text("Acreditación")})

                Spacer(modifier = Modifier.height(10.dp))

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

                    }

                ) {
                    Text("Guardar cambios")
                }

            } else {

                Text("Materia: ${kardex.Materia}")
                Text("Clave Materia: ${kardex.ClvMat}")
                Text("Clave Oficial: ${kardex.ClvOfiMat}")
                Text("Créditos: ${kardex.Cdts}")
                Text("Calificación: ${kardex.Calif}")
                Text("Acreditación: ${kardex.Acred}")

                Spacer(modifier = Modifier.height(10.dp))

                Row {

                    Button(
                        onClick = { editing = true }
                    ) {
                        Text("Editar")
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

}