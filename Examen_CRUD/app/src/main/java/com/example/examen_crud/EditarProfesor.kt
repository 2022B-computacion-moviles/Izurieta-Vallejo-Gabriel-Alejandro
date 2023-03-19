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

class EditarProfesor : AppCompatActivity() {

    var selectedProfesorCode: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_editar_profesor)
        selectedProfesorCode = intent.getIntExtra("profesorSelected", 0)
        llenarInfo()
        editarProfesor()
    }

    fun llenarInfo(){
        val nombre = findViewById<EditText>(R.id.nombreProfesorEditar)
        val fechaInicio = findViewById<EditText>(R.id.fechaInicioEditar)
        val sueldo = findViewById<EditText>(R.id.salarioEditar)
        val numAsignaturas = findViewById<EditText>(R.id.numAsignaturaEditar)
        FB_Global.firebaseProfesor.read(selectedProfesorCode!!, onSuccess = {profesor->
            nombre.setText(profesor!!.nombreProfesor)
            fechaInicio.setText(profesor!!.fecha_Inicio.toString())
            sueldo.setText(profesor!!.salarioProfesor.toString())
            numAsignaturas.setText(profesor!!.cantidad_asignaturas.toString())
        })
    }

    fun editarProfesor()
    {
        val nombre = findViewById<EditText>(R.id.nombreProfesorEditar)
        val fechaInicio = findViewById<EditText>(R.id.fechaInicioEditar)
        val sueldo = findViewById<EditText>(R.id.salarioEditar)
        val cantAsignatura= findViewById<EditText>(R.id.numAsignaturaEditar)
        val botonGuardar = findViewById<Button>(R.id.btn_EditarProfesor)
        botonGuardar.setOnClickListener {

            FB_Global.firebaseProfesor.update(
                Profesor(
                    selectedProfesorCode!!,nombre.text.toString(),
                LocalDate.parse(fechaInicio.text.toString()),
                sueldo.text.toString().toDouble()
                ,cantAsignatura.text.toString().toInt()
            )
            )
            Toast.makeText(this, "Se editó al Profesor", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

    }

}