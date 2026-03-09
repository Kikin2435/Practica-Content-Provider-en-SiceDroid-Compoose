package com.example.sicedroidcontentprovider.ui.ViewModel

import android.content.ContentValues
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
    private val kardex_uri = Uri.parse("content://com.example.myapplication.provider/kardex")
    private val cargaAcademica_uri = Uri.parse("content://com.example.myapplication.provider/carga_academica")
    private val perfil_uri = Uri.parse("content://com.example.myapplication.provider/perfil")

    var listaKardex by mutableStateOf<List<Kardex>>(emptyList())
        private set

    var listaCargaAcademica by mutableStateOf<List<CargaAcademica>>(emptyList())
        private set

    // Estados para el Login
    var loginError by mutableStateOf<String?>(null)
        private set

    fun verificarSesionIniciada(
        context: Context, 
        matricula: String, 
        password: String, 
        onSuccess: () -> Unit
    ) {
        loginError = null
        try {
            val cursor = context.contentResolver.query(perfil_uri, null, null, null, null)
            val esValido = cursor?.use {
                if (it.moveToFirst()) {
                    val matriculaG = it.getString(it.getColumnIndexOrThrow("matricula"))
                    val passwordG = it.getString(it.getColumnIndexOrThrow("password"))
                    
                    Log.d("LOGIN", "Credenciales Provider: $matriculaG / $passwordG")
                    
                    matricula == matriculaG && password == passwordG
                } else false
            } ?: false

            if (esValido) {
                onSuccess()
            } else {
                loginError = "Matrícula o contraseña incorrectas"
            }
        } catch (e: Exception) {
            Log.e("LOGIN", "Error al conectar con el Provider", e)
            loginError = "No se pudo conectar con la aplicación principal"
        }
    }

    // --- KARDEX ---
    fun obtenerKardexExterno(context: Context) {
        val lista = mutableListOf<Kardex>()
        try {
            val cursor = context.contentResolver.query(kardex_uri, null, null, null, null)
            cursor?.use {
                val idIndex = it.getColumnIndex("id")
                while (it.moveToNext()) {
                    val item = Kardex(
                        id = if (idIndex != -1) it.getInt(idIndex) else 0,
                        Materia = it.getString(it.getColumnIndexOrThrow("Materia")),
                        Calif = it.getInt(it.getColumnIndexOrThrow("Calif")),
                        Cdts = it.getInt(it.getColumnIndexOrThrow("Cdts")),
                        ClvMat = it.getString(it.getColumnIndexOrThrow("ClvMat")),
                        ClvOfiMat = it.getString(it.getColumnIndexOrThrow("ClvOfiMat")),
                        Acred = it.getString(it.getColumnIndexOrThrow("Acred"))
                    )
                    lista.add(item)
                }
            }
            listaKardex = lista
        } catch (e: Exception) {
            Log.e("VWC", "Error al obtener Kardex", e)
        }
    }

    fun insertarKardex(context: Context, item: Kardex) {
        try {
            val values = mapKardexToValues(item)
            values.remove("id")
            val uri = context.contentResolver.insert(kardex_uri, values)
            if (uri != null) obtenerKardexExterno(context)
        } catch (e: Exception) {
            Log.e("VWC", "Error al insertar Kardex", e)
        }
    }

    fun actualizarKardex(context: Context, item: Kardex) {
        try {
            val values = mapKardexToValues(item)
            val rows = context.contentResolver.update(kardex_uri, values, "id = ?", arrayOf(item.id.toString()))
            if (rows > 0) obtenerKardexExterno(context)
        } catch (e: Exception) {
            Log.e("VWC", "Error al actualizar Kardex", e)
        }
    }

    fun eliminarKardex(context: Context, item: Kardex) {
        try {
            val rows = context.contentResolver.delete(kardex_uri, "id = ?", arrayOf(item.id.toString()))
            if (rows > 0) obtenerKardexExterno(context)
        } catch (e: Exception) {
            Log.e("VWC", "Error al eliminar Kardex", e)
        }
    }

    // --- CARGA ACADEMICA ---
    fun obtenerCargaAcademica(context: Context) {
        val lista = mutableListOf<CargaAcademica>()
        try {
            val cursor = context.contentResolver.query(cargaAcademica_uri, null, null, null, null)
            cursor?.use {
                while (it.moveToNext()) {
                    val item = CargaAcademica(
                        id = it.getInt(it.getColumnIndexOrThrow("id")),
                        Materia = it.getString(it.getColumnIndexOrThrow("Materia")),
                        Docente = it.getString(it.getColumnIndexOrThrow("Docente")),
                        Grupo = it.getString(it.getColumnIndexOrThrow("Grupo")),
                        CreditosMateria = it.getInt(it.getColumnIndexOrThrow("CreditosMateria")),
                        EstadoMateria = it.getString(it.getColumnIndexOrThrow("EstadoMateria")),
                        Lunes = it.getString(it.getColumnIndexOrThrow("Lunes")),
                        Martes = it.getString(it.getColumnIndexOrThrow("Martes")),
                        Miercoles = it.getString(it.getColumnIndexOrThrow("Miercoles")),
                        Jueves = it.getString(it.getColumnIndexOrThrow("Jueves")),
                        Viernes = it.getString(it.getColumnIndexOrThrow("Viernes")),
                        Sabado = it.getString(it.getColumnIndexOrThrow("Sabado")),
                        Observaciones = it.getString(it.getColumnIndexOrThrow("Observaciones")),
                        Semipresencial = it.getString(it.getColumnIndexOrThrow("Semipresencial")),
                        clvOficial = it.getString(it.getColumnIndexOrThrow("clvOficial"))
                    )
                    lista.add(item)
                }
            }
            listaCargaAcademica = lista
        } catch (e: Exception) {
            Log.e("VWC", "Error al obtener Carga", e)
        }
    }

    fun insertarCargaAcademica(context: Context, item: CargaAcademica) {
        try {
            val values = mapCargaToValues(item)
            values.remove("id")
            val uri = context.contentResolver.insert(cargaAcademica_uri, values)
            if (uri != null) obtenerCargaAcademica(context)
        } catch (e: Exception) {
            Log.e("VWC", "Error al insertar Carga", e)
        }
    }

    fun actualizarCargaAcademica(context: Context, item: CargaAcademica) {
        try {
            val values = mapCargaToValues(item)
            val rows = context.contentResolver.update(cargaAcademica_uri, values, "id = ?", arrayOf(item.id.toString()))
            if (rows > 0) obtenerCargaAcademica(context)
        } catch (e: Exception) {
            Log.e("VWC", "Error al actualizar Carga", e)
        }
    }

    fun eliminarCargaAcademica(context: Context, item: CargaAcademica) {
        try {
            val rows = context.contentResolver.delete(cargaAcademica_uri, "id = ?", arrayOf(item.id.toString()))
            if (rows > 0) obtenerCargaAcademica(context)
        } catch (e: Exception) {
            Log.e("VWC", "Error al eliminar Carga", e)
        }
    }

    private fun mapKardexToValues(item: Kardex): ContentValues {
        return ContentValues().apply {
            put("id", item.id)
            put("Materia", item.Materia)
            put("Calif", item.Calif)
            put("Cdts", item.Cdts)
            put("ClvMat", item.ClvMat)
            put("ClvOfiMat", item.ClvOfiMat)
            put("Acred", item.Acred)
        }
    }

    private fun mapCargaToValues(item: CargaAcademica): ContentValues {
        return ContentValues().apply {
            put("id", item.id)
            put("Materia", item.Materia)
            put("Docente", item.Docente)
            put("Grupo", item.Grupo)
            put("CreditosMateria", item.CreditosMateria)
            put("EstadoMateria", item.EstadoMateria)
            put("Lunes", item.Lunes)
            put("Martes", item.Martes)
            put("Miercoles", item.Miercoles)
            put("Jueves", item.Jueves)
            put("Viernes", item.Viernes)
            put("Sabado", item.Sabado)
            put("Observaciones", item.Observaciones)
            put("Semipresencial", item.Semipresencial)
            put("clvOficial", item.clvOficial)
        }
    }
}
