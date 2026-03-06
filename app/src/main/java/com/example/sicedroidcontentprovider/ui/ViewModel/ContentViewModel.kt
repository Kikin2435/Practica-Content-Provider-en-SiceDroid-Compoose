package com.example.sicedroidcontentprovider.ui.ViewModel

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.sicedroidcontentprovider.data.CargaAcademica
import com.example.sicedroidcontentprovider.data.Kardex

class ContentViewModel : ViewModel() {
    // CORRECCIÓN: La dirección debe terminar en /kardex, no en /1
    private val kardex_uri = Uri.parse("content://com.example.myapplication.provider/kardex")
    private val cargaAcademica_uri = Uri.parse("content://com.example.myapplication.provider/carga_academica")

    var listaKardex by mutableStateOf<List<Kardex>>(emptyList())
        private set

    var listaCargaAcademica by mutableStateOf<List<CargaAcademica>>(emptyList())
        private set

    fun obtenerKardexExterno(context: Context) {
        Log.d("VWC", "--- Función obtenerKardexExterno llamada ---")
        val lista = mutableListOf<Kardex>()
        try {
            val cursor = context.contentResolver.query(kardex_uri, null, null, null, null)

            if (cursor == null) {
                Log.e("VWC", "El cursor regresó NULL. ¿Está instalada la otra app?")
                return
            }

            cursor.use {
                Log.d("VWC", "Cursor obtenido con ${it.count} filas")
                while (it.moveToNext()) {
                    val item = Kardex(
                        Materia = it.getString(it.getColumnIndexOrThrow("Materia")),
                        Calif = it.getInt(it.getColumnIndexOrThrow("Calif")),
                        Cdts = it.getInt(it.getColumnIndexOrThrow("Cdts")),
                    )
                    lista.add(item)
                }
            }
            listaKardex = lista
            Log.d("VWC", "Datos procesados: ${lista.size} elementos guardados en el estado")
        } catch (e: Exception) {
            Log.e("VWC", "ERROR CRÍTICO: ${e.message}", e)
        }
    }

    fun obtenerCargaAcademica(context: Context) {
        val lista = mutableListOf<CargaAcademica>()

        try {
            val cursor = context.contentResolver.query(cargaAcademica_uri, null, null, null, null)
            if (cursor == null) {
                Log.e("VWC", "El cursor regresó NULL. ¿Está instalada la otra app?")
                return
            }
            cursor.use {
                Log.d("VWC", "Cursor obtenido con ${it.count} filas")
                while (it.moveToNext()) {
                    val item = CargaAcademica(
                        Materia = it.getString(it.getColumnIndexOrThrow("Materia")),
                        Semipresencial = it.getString(it.getColumnIndexOrThrow("Semipresencial")),
                        Observaciones = it.getString(it.getColumnIndexOrThrow("Observaciones")),
                        Docente = it.getString(it.getColumnIndexOrThrow("Docente")),
                        clvOficial = it.getString(it.getColumnIndexOrThrow("clvOficial")),
                        Sabado = it.getString(it.getColumnIndexOrThrow("Sabado")),
                        Viernes = it.getString(it.getColumnIndexOrThrow("Viernes")),
                        Jueves = it.getString(it.getColumnIndexOrThrow("Jueves")),
                        Miercoles = it.getString(it.getColumnIndexOrThrow("Miercoles")),
                        Martes = it.getString(it.getColumnIndexOrThrow("Martes")),
                        Lunes = it.getString(it.getColumnIndexOrThrow("Lunes")),
                        EstadoMateria = it.getString(it.getColumnIndexOrThrow("EstadoMateria")),
                        CreditosMateria = it.getInt(it.getColumnIndexOrThrow("CreditosMateria")),
                        Grupo = it.getString(it.getColumnIndexOrThrow("Grupo"))
                    )
                    lista.add(item)
                }
            }
            listaCargaAcademica = lista
        } catch (e: Exception){
            Log.e("VWC", "ERROR CRITICO ${e.message}")
        }
    }
}
