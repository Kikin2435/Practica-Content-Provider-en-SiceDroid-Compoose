package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.sicedroidcontentprovider.data.CargaAcademica
import com.example.sicedroidcontentprovider.ui.components.CargaCard

@Composable
fun CargaScreen(
    listaCarga: List<CargaAcademica>,
    onInsertClick: () -> Unit,
    onDelete: (CargaAcademica) -> Unit,
    onEdit: (CargaAcademica) -> Unit
) {

    Scaffold(

        floatingActionButton = {
            FloatingActionButton(
                onClick = { onInsertClick() }
            ) {
                Text("+")
            }
        }

    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(10.dp)
        ) {

            items(listaCarga) { carga ->

                CargaCard(
                    carga = carga,
                    onDelete = { onDelete(carga) },
                    onUpdate = { nuevaCarga -> onEdit(nuevaCarga) }
                )

            }

        }

    }
}