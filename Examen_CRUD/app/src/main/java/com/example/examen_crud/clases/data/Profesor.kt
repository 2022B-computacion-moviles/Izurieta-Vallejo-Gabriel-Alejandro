package com.example.examen_crud.clases.data

import java.time.LocalDate

class Profesor (
    val codeProfesor: Int,
    var nombreProfesor: String,
    var fecha_Inicio: LocalDate,
    var salarioProfesor: Double,
    var cantidad_asignaturas: Int
)