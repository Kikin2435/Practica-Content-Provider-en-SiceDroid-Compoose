package com.example.sicedroidcontentprovider.ui.navigation

import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.sicedroidcontentprovider.data.CargaAcademica
import com.example.sicedroidcontentprovider.ui.screens.*
import com.example.sicedroidcontentprovider.data.Kardex
import com.example.sicedroidcontentprovider.ui.ViewModel.ContentViewModel

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val viewModel: ContentViewModel = viewModel()
    val context = LocalContext.current

    // Estados locales para manejo de UI
    var listaCarga by remember { mutableStateOf<List<CargaAcademica>>(emptyList()) }
    var listaKardex by remember { mutableStateOf<List<Kardex>>(emptyList()) }

    // Sincronizamos la lista del ViewModel con nuestra lista local cuando el ViewModel cambie
    LaunchedEffect(viewModel.listaKardex) {
        listaKardex = viewModel.listaKardex
    }

    LaunchedEffect(viewModel.listaCargaAcademica) {
        listaCarga = viewModel.listaCargaAcademica
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(onLoginSuccess = {
                navController.navigate("menu") {
                    popUpTo("login") { inclusive = true }
                }
            })
        }

        composable("menu") {
            MenuScreen(
                onCargaClick = { navController.navigate("carga") },
                onKardexClick = { navController.navigate("kardex") }
            )
        }

        composable("carga") {
            LaunchedEffect(Unit) {
                viewModel.obtenerCargaAcademica(context)
            }

            MainScreen(
                navController = navController,
                lista = listaCarga,
                onInsertClick = { navController.navigate("insertarCarga") },
                onDelete = { index -> listaCarga = listaCarga.filterIndexed { i, _ -> i != index } },
                onUpdate = { index, nueva ->
                    val mutable = listaCarga.toMutableList()
                    mutable[index] = nueva
                    listaCarga = mutable
                }
            )
        }

        composable("kardex") {
            // CARGA BAJO DEMANDA: Cargamos los datos justo cuando entramos a esta pantalla
            LaunchedEffect(Unit) {
                viewModel.obtenerKardexExterno(context)
            }

            KardexScreen(
                listaKardex = listaKardex,
                onInsertClick = { navController.navigate("insertKardex") },
                onDelete = { index ->
                    listaKardex = listaKardex.filterIndexed { i, _ -> i != index }
                },
                onUpdate = { index, nuevo ->
                    val mutable = listaKardex.toMutableList()
                    mutable[index] = nuevo
                    listaKardex = mutable
                },
                onBack = { navController.popBackStack() }
            )
        }

        composable("insertKardex") {
            InsertScreen(
                navController = navController,
                onGuardar = { nuevo ->
                    listaKardex = listaKardex + nuevo
                    navController.popBackStack()
                }
            )
        }
        
        composable("insertarCarga") {
            InsertCargaScreen(
                navController = navController,
                onGuardar = { nueva ->
                    listaCarga = listaCarga + nueva
                    navController.popBackStack()
                }
            )
        }
    }
}
