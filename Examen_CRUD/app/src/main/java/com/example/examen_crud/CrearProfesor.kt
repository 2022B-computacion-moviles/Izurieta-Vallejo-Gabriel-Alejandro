package com.example.examen_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.examen_crud.clases.data.Profesor
import com.example.examen_crud.clases.firebase.FB_Global
import java.time.LocalDate

class CrearProfesor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_profesor)
        guardarProfesor()
    }

    fun guardarProfesor()
    {
        val nombre = findViewById<EditText>(R.id.nombreProfesorGuardar)
        val fechaInicio = findViewById<EditText>(R.id.fechaInicioGuardar)
        val sueldo = findViewById<EditText>(R.id.salarioGuardar)
        val numAlumnos = findViewById<EditText>(R.id.numAsignaturaGuardar)
        val botonGuardar = findViewById<Button>(R.id.btn_GuardarProfesor)
        botonGuardar.setOnClickListener {

            FB_Global.firebaseProfesor.create(Profesor(
                0,nombre.text.toString(),
                LocalDate.parse(fechaInicio.text.toString()),
                sueldo.text.toString().toDouble()
                ,numAlumnos.text.toString().toInt()
            ))
            Toast.makeText(this, "Se añadió un Nuevo Profesor", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }
}