package com.example.examen_crud

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.clases.adapter.RcVwAdapterProfesores
import com.example.examen_crud.clases.data.Profesor
import com.example.examen_crud.clases.firebase.FB_Global
import java.time.LocalDate

class MainActivity : AppCompatActivity() {

    private lateinit var selectedProfesor: Profesor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val rvProfesores=findViewById<RecyclerView>(R.id.rvw_verProfesores)
        FB_Global.firebaseProfesor.getAllProfesores { profesores ->
            if(profesores.isEmpty()){
                FB_Global.firebaseProfesor.create(
                    Profesor(
                        0,"Marco", LocalDate.parse("2022-03-11"),2000.10,5
                    )
                )
                FB_Global.firebaseProfesor.create(
                    Profesor(
                        2,"Saul", LocalDate.parse("2022-03-12"),2000.10,5
                    )
                )
            }
        }

        FB_Global.firebaseProfesor.getAllProfesores { profesores ->
            initializeRecyclerView(profesores, rvProfesores )
            registerForContextMenu(rvProfesores)
        }

        //Button
        val rvCrearButton = findViewById<Button>(R.id.btn_crearProfesor)
        rvCrearButton.setOnClickListener {
            val intent = Intent(this, CrearProfesor::class.java)
            startActivity(intent)
        }


    }

    private fun initializeRecyclerView(
        list: ArrayList<Profesor>,
        recyclerView: RecyclerView
    ) {
        val manager= LinearLayoutManager(this)
        val decoration= DividerItemDecoration(this, manager.orientation)

        val adapter = RcVwAdapterProfesores(this, list, {profesor ->onProfesorSelected(profesor)})

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = manager
        recyclerView.addItemDecoration(decoration)

        adapter.notifyDataSetChanged()
    }

    fun setSelectedProfesorCode(profesor: Profesor) {
        selectedProfesor = profesor
    }

    fun onProfesorSelected(profesor: Profesor) {
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {

            R.id.menuProfesor_Ver-> {
                val intent = Intent(this, ListaAsignatura::class.java)
                intent.putExtra("profesorSelected",selectedProfesor.codeProfesor)
                startActivity(intent)
                return true
            }
            R.id.menuProfesor_editar-> {
                startActivity(Intent(this, EditarProfesor::class.java).apply {
                    putExtra("profesorSelected",selectedProfesor.codeProfesor)
                })
                return true
            }
            R.id.menuProfesor_Eliminar-> {
                FB_Global.firebaseProfesor.delete(selectedProfesor.codeProfesor)
                Toast.makeText(this, "Se eliminó un Profesor", Toast.LENGTH_SHORT).show()
                val rvProfesores=findViewById<RecyclerView>(R.id.rvw_verProfesores)
                FB_Global.firebaseProfesor.getAllProfesores { profesores ->
                    initializeRecyclerView(profesores, rvProfesores )
                    registerForContextMenu(rvProfesores)
                }

                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}

