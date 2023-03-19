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
import com.example.examen_crud.clases.adapter.RcVwAdapterAsignaturas
import com.example.examen_crud.clases.data.Asignatura
import com.example.examen_crud.clases.firebase.FB_Global

class ListaAsignatura : AppCompatActivity() {
    var selectedProfesorCode: Int? = null
    private lateinit var selectedAsignatura: Asignatura
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_asignatura)
        selectedProfesorCode = intent.getIntExtra("profesorSelected", 0)

        val rvAsignatura=findViewById<RecyclerView>(R.id.rvw_ListarAsignaturas)
        FB_Global.firebaseAsignatura.getAllAsignaturasByProfesor(selectedProfesorCode!!
        ) { asignaturas ->
            if (asignaturas.isEmpty()) {

                FB_Global.firebaseAsignatura.create(
                    Asignatura(
                        0, selectedProfesorCode!!, "Matemáticas", 42.2, false, 9
                    )
                )
                FB_Global.firebaseAsignatura.create(
                    Asignatura(
                        0, selectedProfesorCode!!, "Inglés", 42.2, false, 15
                    )
                )
            }

        }

        FB_Global.firebaseAsignatura.getAllAsignaturasByProfesor(selectedProfesorCode!!
        ) { asignaturas ->
            initializeRecyclerView(asignaturas, rvAsignatura )
            registerForContextMenu(rvAsignatura)
        }

        //Button
        val rvAniadirButton = findViewById<Button>(R.id.btn_aniadir_asignatura)
        rvAniadirButton.setOnClickListener {
            val intent = Intent(this, CrearAsignatura::class.java)
            intent.putExtra("selectedProfesorCode",selectedProfesorCode)
            startActivity(intent)
        }
    }


    private fun initializeRecyclerView(
        list: ArrayList<Asignatura>,
        recyclerView: RecyclerView
    ) {
        val manager= LinearLayoutManager(this)
        val decoration= DividerItemDecoration(this, manager.orientation)

        val adapter = RcVwAdapterAsignaturas(this, list, {asignatura ->onAsignaturaSelected(asignatura)})

        recyclerView.adapter = adapter
        recyclerView.itemAnimator = androidx.recyclerview.widget.DefaultItemAnimator()
        recyclerView.layoutManager = manager
        recyclerView.addItemDecoration(decoration)

        adapter.notifyDataSetChanged()
    }

    fun setSelectedAsignaturaCode(asignatura: Asignatura) {
        selectedAsignatura = asignatura
    }

    fun onAsignaturaSelected(asignatura: Asignatura) {
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_AsignaturaEliminar-> {
                FB_Global.firebaseAsignatura.delete(selectedAsignatura)
                Toast.makeText(this, "Se eliminó la Asignatura", Toast.LENGTH_SHORT).show()
                val rvAsignatura=findViewById<RecyclerView>(R.id.rvw_ListarAsignaturas)
                FB_Global.firebaseAsignatura.getAllAsignaturasByProfesor(selectedProfesorCode!!) { asignaturas ->
                    initializeRecyclerView(asignaturas, rvAsignatura )
                    registerForContextMenu(rvAsignatura)
                }
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


}