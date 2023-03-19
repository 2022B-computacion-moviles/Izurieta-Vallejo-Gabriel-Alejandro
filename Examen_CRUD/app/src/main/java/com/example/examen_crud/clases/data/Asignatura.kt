package com.example.examen_crud.clases.data

import java.io.Serializable
import java.time.LocalDate

class Asignatura (
    val codeAsignatura: Int,
    val codeProfesor: Int,

    var nombreAsignatura: String,
    var costoMateriales: Double,
    var esVespertina: Boolean,
    var numeroAlumnos: Int
): Serializable