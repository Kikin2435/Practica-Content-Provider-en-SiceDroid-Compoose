package com.example.sicedroidcontentprovider.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MenuScreen(

    onCargaClick: () -> Unit,
    onKardexClick: () -> Unit

) {

    Column(

        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),

        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {

        Button(

            onClick = { onCargaClick() },
            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Ver Carga Académica")

        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(

            onClick = { onKardexClick() },
            modifier = Modifier.fillMaxWidth()

        ) {

            Text("Ver Kardex")

        }

    }

}