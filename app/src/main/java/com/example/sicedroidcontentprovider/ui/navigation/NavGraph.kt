package com.example.sicedroidcontentprovider.ui.navigation

import androidx.compose.runtime.*
import androidx.navigation.compose.*
import androidx.navigation.compose.rememberNavController
import com.example.sicedroidcontentprovider.data.CargaAcademica
import com.example.sicedroidcontentprovider.ui.screens.*
import com.example.sicedroidcontentprovider.data.Kardex

@Composable
fun NavGraph() {

    val navController = rememberNavController()

    var listaCarga by remember {
        mutableStateOf<List<CargaAcademica>>(emptyList())
    }

    var listaKardex by remember {
        mutableStateOf<List<Kardex>>(emptyList())
    }

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {

        // LOGIN
        composable("login") {

            LoginScreen(

                onLoginSuccess = {
                    navController.navigate("menu") {
                        popUpTo("login") { inclusive = true }
                    }
                }

            )

        }

        // MENU PRINCIPAL
        composable("menu") {

            MenuScreen(
                onCargaClick = {
                    navController.navigate("carga")
                },
                onKardexClick = {
                    navController.navigate("kardex")
                }
            )

        }

        // PANTALLA CARGA
        composable("carga") {

            MainScreen(

                navController = navController,

                lista = listaCarga,

                onInsertClick = {
                    navController.navigate("insertarCarga")
                },

                onDelete = { index ->
                    listaCarga = listaCarga.toMutableList().also {
                        it.removeAt(index)
                    }
                },

                onUpdate = { index, nueva ->
                    listaCarga = listaCarga.toMutableList().also {
                        it[index] = nueva
                    }
                }

            )

        }

        // INSERTAR CARGA
        composable("insertarCarga") {

            InsertCargaScreen(

                navController = navController,

                onGuardar = { nuevaCarga ->

                    listaCarga = listaCarga + nuevaCarga
                    navController.popBackStack()

                }

            )

        }

        // PANTALLA KARDEX
        composable("kardex") {

            KardexScreen(

                listaKardex = listaKardex,

                onInsertClick = {
                    navController.navigate("insertKardex")
                },

                onDelete = { index ->
                    listaKardex = listaKardex.toMutableList().also {
                        it.removeAt(index)
                    }
                },

                onUpdate = { index, nuevo ->
                    listaKardex = listaKardex.toMutableList().also {
                        it[index] = nuevo
                    }
                },

                onBack = {
                    navController.popBackStack()
                }

            )

        }

        composable("insertKardex") {

            InsertScreen(

                navController = navController,

                onGuardar = { nuevoKardex ->

                    listaKardex = listaKardex + nuevoKardex
                    navController.popBackStack()

                }

            )

        }

    }

}