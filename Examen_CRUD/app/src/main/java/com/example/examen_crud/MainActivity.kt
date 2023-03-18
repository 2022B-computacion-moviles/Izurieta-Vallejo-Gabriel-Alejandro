package com.example.examen_crud

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        FB_Global.firebaseProfesor.create_Update(
            Profesor(
                0,"Marco", LocalDate.parse("11-03-2022"),2000.10,5
            )
        )
        FB_Global.firebaseProfesor.create_Update(
            Profesor(
                2,"Saul", LocalDate.parse("11-03-2022"),2000.10,5
            )
        )

        val rvProfesores=findViewById<RecyclerView>(R.id.rvw_verProfesores)
        FB_Global.firebaseProfesor.getAllProfesores { profesores ->
            initializeRecyclerView(profesores, rvProfesores )
            registerForContextMenu(rvProfesores)
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

}