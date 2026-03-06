package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
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

    val azulTecNM = Color(0xFF003366)

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
                            "Kardex",
                            color = Color.White
                        )
                    }

                },

                navigationIcon = {

                    IconButton(onClick = { onBack() }) {

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

        },

        floatingActionButton = {

            FloatingActionButton(

                onClick = { onInsertClick() },
                containerColor = azulTecNM

            ) {

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Agregar",
                    tint = Color.White
                )

            }

        }

    ) { padding ->

        if (listaKardex.isEmpty()) {

            Box(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),

                contentAlignment = androidx.compose.ui.Alignment.Center

            ) {

                Text(
                    "No hay materias en el Kardex",
                    style = MaterialTheme.typography.titleMedium
                )

            }

        } else {

            LazyColumn(

                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),

                contentPadding = PaddingValues(12.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)

            ) {

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

}