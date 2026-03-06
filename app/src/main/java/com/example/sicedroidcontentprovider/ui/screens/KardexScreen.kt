package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.sicedroidcontentprovider.data.Kardex
import com.example.sicedroidcontentprovider.ui.components.KardexCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun KardexScreen(

    listaKardex: List<Kardex>,
    onInsertClick: () -> Unit,
    onDelete: (Int) -> Unit,
    onUpdate: (Int, Kardex) -> Unit,
    onBack: () -> Unit

) {

    Scaffold(

        topBar = {
            TopAppBar(
                title = { Text("Kardex") },
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar"
                        )
                    }
                }
            )
        },

        floatingActionButton = {

            FloatingActionButton(
                onClick = { onInsertClick() }
            ) {
                Text("+")
            }

        }

    ) { padding ->

        LazyColumn(contentPadding = padding) {

            itemsIndexed(listaKardex) { index, item ->

                KardexCard(

                    kardex = item,

                    onDelete = {
                        onDelete(index)
                    },

                    onUpdate = { nuevo ->
                        onUpdate(index, nuevo)
                    }

                )

            }

        }

    }

}