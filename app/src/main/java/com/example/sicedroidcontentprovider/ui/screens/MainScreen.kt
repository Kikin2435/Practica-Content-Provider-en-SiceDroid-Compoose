package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.sicedroidcontentprovider.data.CargaAcademica
import com.example.sicedroidcontentprovider.ui.ViewModel.ContentViewModel
import com.example.sicedroidcontentprovider.ui.components.CargaCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavController,
    lista: List<CargaAcademica>,
    onInsertClick: () -> Unit,
    viewModel: ContentViewModel = viewModel()
) {
    val context = LocalContext.current
    val azulTecNM = Color(0xFF1B396A)

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        "Carga Académica",
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = azulTecNM
                ),
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Regresar",
                            tint = Color.White
                        )
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onInsertClick() },
                containerColor = azulTecNM,
                contentColor = Color.White
            ) {
                Text(
                    text = "+"
                )
            }
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(10.dp)
        ) {
            itemsIndexed(lista) { index, item ->
                CargaCard(
                    carga = item,
                    onDelete = {
                         viewModel.eliminarCargaAcademica(context, item)
                    },
                    onUpdate = { nueva ->
                        viewModel.actualizarCargaAcademica(context, nueva)
                    }
                )
            }
        }
    }
}
