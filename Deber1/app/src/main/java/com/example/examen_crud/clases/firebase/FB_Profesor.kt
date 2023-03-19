package com.example.examen_crud.clases.firebase

import com.example.examen_crud.clases.data.Profesor
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.time.LocalDate
import java.util.*
import kotlin.collections.ArrayList

class FB_Profesor {

    private val db = Firebase.firestore
    private val profesorCollectionReference = db.collection("profesores")

    fun getAllProfesores(onSuccess: (ArrayList<Profesor>) -> Unit) {
        profesorCollectionReference
            .get()
            .addOnSuccessListener { documents ->
                val profesores = ArrayList<Profesor>()


                for (document in documents) {
                    profesores.add(
                        Profesor(
                            codeProfesor = document.id.split("/").last().toInt(),
                            nombreProfesor = document.getString("nombre-profesor")!!,
                            fecha_Inicio = LocalDate.parse(document.getString("fecha_inicio")!!),
                            salarioProfesor = document.getDouble("salario-profesor")!!,
                            cantidad_asignaturas =  document.getDouble("cantidad_asignaturas")!!.toInt()
                        )

                    )
                }

                onSuccess(profesores)
            }
    }

    fun create(entity: Profesor) {
        val profesor = hashMapOf(
            "nombre-profesor" to entity.nombreProfesor,
            "fecha_inicio" to entity.fecha_Inicio.toString(),
            "salario-profesor" to entity.salarioProfesor,
            "cantidad_asignaturas" to entity.cantidad_asignaturas
        )
        profesorCollectionReference.document(randomID().toString()).set(profesor)
    }

    fun update(entity: Profesor) {
        val profesor = hashMapOf(
            "nombre-profesor" to entity.nombreProfesor,
            "fecha_inicio" to entity.fecha_Inicio.toString(),
            "salario-profesor" to entity.salarioProfesor,
            "cantidad_asignaturas" to entity.cantidad_asignaturas
        )
        profesorCollectionReference.document(entity.codeProfesor.toString()).set(profesor)
    }

    fun read(code: Int, onSuccess: (Profesor) -> Unit) {
        val profesorReference = profesorCollectionReference.document(code.toString())

        profesorReference
            .get()
            .addOnSuccessListener { document ->
                if (document.exists()) {
                    val profesor = Profesor(
                        codeProfesor =  code,
                        nombreProfesor = document.getString("nombre-profesor")!!,
                        fecha_Inicio = LocalDate.parse(document.getString("fecha_inicio")!!),
                        salarioProfesor = document.getDouble("salario-profesor")!!,
                        cantidad_asignaturas =  document.getDouble("cantidad_asignaturas")!!.toInt()
                    )

                    onSuccess(profesor)
                }
            }
    }
    fun delete(code: Int) {
        val profesorReference = profesorCollectionReference.document(code.toString())
        profesorReference.delete()
    }

    fun randomID() :Int {
        var identificador = Date().time.toInt()
        return identificador
    }

}