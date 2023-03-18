package com.example.examen_crud.clases.firebase

import com.example.examen_crud.clases.data.Asignatura
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.*
import kotlin.collections.ArrayList

class FB_Asignatura {
    private val db = Firebase.firestore
    private val profesorCollectionReference = db.collection("profesores")

    fun getAllAsignaturasByProfesor(
        profesorCode: Int,
        onSuccess: (ArrayList<Asignatura>) -> Unit
    ) {
        profesorCollectionReference
            .document(profesorCode.toString())
            .collection("asignaturas")
            .get()
            .addOnSuccessListener { documents ->
                val asignaturas = ArrayList<Asignatura>()

                for (document in documents) {
                    asignaturas.add(
                        Asignatura(
                            codeAsignatura = document.id.split("/").last().toInt(),
                            codeProfesor = profesorCode,
                            nombreAsignatura = document.getString("marca")!!,
                            costoMateriales = document.getDouble("precio")!!,
                            esVespertina = document.getBoolean("color_subjetivo")!!,
                            numeroAlumnos = document.getDouble("meses_plazo_pagar")!!.toInt()
                        )
                    )
                }

                onSuccess(asignaturas)
            }
    }

    fun create_Update(entity: Asignatura) {
        val asignatura = hashMapOf(
            "nombreAsignatura" to entity.nombreAsignatura,
            "costoMateriales" to entity.costoMateriales,
            "esVespertina" to entity.esVespertina,
            "numeroAlumnos" to entity.numeroAlumnos,
        )

        profesorCollectionReference
            .document(entity.codeProfesor.toString())
            .collection("asignaturas")
            .document(randomID().toString()).set(asignatura)
    }

    fun read(code: Int, onSuccess: (Asignatura) -> Unit) {
        FB_Global.firebaseProfesor.getAllProfesores{ documents ->
            for (document in documents) {
                val db = Firebase.firestore
                val asignaturaCollectionReference = db.collection(
                    "profesores/${document.codeProfesor}/asignaturas"
                )

                asignaturaCollectionReference
                    .get()
                    .addOnSuccessListener { documentsAsignatura ->
                        for (asignatura in documentsAsignatura) {
                            if (asignatura.id.toInt() == code) {
                                onSuccess(
                                    Asignatura(
                                        asignatura.id.toInt(),
                                        document.codeProfesor,
                                        asignatura.getString("nombreAsignatura")!!,
                                        asignatura.getDouble("costoMateriales")!!,
                                        asignatura.getBoolean("esVespertina")!!,
                                        asignatura.getDouble("numeroAlumnos")!!.toInt()
                                    )
                                )
                            }
                        }
                    }
            }
        }
    }

    fun delete(code: Int, onSuccess: (Unit) -> Unit) {
        FB_Global.firebaseProfesor.getAllProfesores{ documents ->
            for (document in documents) {
                val db = Firebase.firestore
                val asignaturaCollectionReference = db.collection(
                    "profesores/${document.codeProfesor}/asignaturas"
                )

                asignaturaCollectionReference
                    .get()
                    .addOnSuccessListener { documentsAsinaturas ->
                        for (documentAsignatura in documentsAsinaturas) {
                            if (documentAsignatura.id.toInt() == code) {
                                val asignaturaReference = asignaturaCollectionReference
                                    .document(code.toString())

                                asignaturaReference.delete().addOnSuccessListener {
                                    onSuccess(Unit)
                                }
                            }
                        }
                    }
            }
        }
    }

    fun randomID() :Int {
        var identificador = Date().time.toInt()
        return identificador
    }
}