package com.example.examen_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen_crud.clases.data.Asignatura
import com.example.examen_crud.clases.firebase.FB_Global

class CrearAsignatura : AppCompatActivity() {
    var selectedProfesorCode: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_asignatura)
        selectedProfesorCode = intent.getIntExtra("selectedProfesorCode", 0)
        crearAsignatura()
    }

    fun crearAsignatura()
    {
        val nombreAsignatura = findViewById<EditText>(R.id.crearAsignatura_Nombre)
        val costo = findViewById<EditText>(R.id.crearAsignatura_Costo)
        val esVespertina = findViewById<EditText>(R.id.crearAsignatura_EsVespertina)
        val numAlumnos = findViewById<EditText>(R.id.crearAsignatura_NumAlumnos)
        val btn_aniadirAsignatura = findViewById<Button>(R.id.btn_crear_asignatura)
        btn_aniadirAsignatura.setOnClickListener {

            FB_Global.firebaseAsignatura.create(
                Asignatura(
                0, selectedProfesorCode!!,
                    nombreAsignatura.text.toString(),
                    costo.text.toString().toDouble(),
                    esVespertina.text.toString().toBoolean(),
                    numAlumnos.text.toString().toInt()
            )
            )
            Toast.makeText(this, "Se creo una Asignatura", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, ListaAsignatura::class.java)
            intent.putExtra("profesorSelected",selectedProfesorCode)
            startActivity(intent)
        }

    }
}