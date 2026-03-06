package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.sicedroidcontentprovider.data.CargaAcademica
import com.example.sicedroidcontentprovider.ui.components.CargaCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(

    navController: NavController,
    lista: List<CargaAcademica>,
    onInsertClick: () -> Unit,
    onDelete: (Int) -> Unit,
    onUpdate: (Int, CargaAcademica) -> Unit

) {

    Scaffold(

        topBar = {

            TopAppBar(

                title = { Text("Carga Académica") },

                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {

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

        LazyColumn(
            modifier = Modifier.padding(padding)
        ) {

            itemsIndexed(lista) { index, item ->

                CargaCard(

                    carga = item,

                    onDelete = {
                        onDelete(index)
                    },

                    onUpdate = { nueva ->
                        onUpdate(index, nueva)
                    }

                )

            }

        }

    }

}