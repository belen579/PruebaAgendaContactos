package com.example.pruebaagendacontactos.ui.viewmodel

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import java.io.File


class Contactosviewmodel(private val context: Context) :ViewModel(){


    var nombre :String by mutableStateOf("")
    var telefono :String by mutableStateOf("")
    var contactos: List<Contacto> by mutableStateOf(emptyList())

    init {
        // Al inicializar el ViewModel, cargamos los contactos
        cargarContactos()
    }

    fun cargarContactos() {
        contactos = obtenerContactos()
    }


    fun agregarContacto(contacto: Contacto) {

        val file = File(context.filesDir, "contactos.txt")



        file.appendText("${contacto.nombre},${contacto.telefono}\n")
        cargarContactos()
    }



    fun obtenerContactos(): List<Contacto> {
        val file = File(context.filesDir, "contactos.txt")
        if (!file.exists()) return emptyList()

        return file.readLines().map {
            val (nombre, telefono) = it.split(",")
            Contacto(nombre, telefono)
        }
    }

    fun eliminarContacto( contacto: Contacto) {
        val file = File(context.filesDir, "contactos.txt")
        val contactosActualizados = contactos.toMutableList()
        contactosActualizados.remove(contacto)
        file.writeText("") // Limpiar el archivo
        contactosActualizados.forEach { agregarContacto(it) }

    }

    }



