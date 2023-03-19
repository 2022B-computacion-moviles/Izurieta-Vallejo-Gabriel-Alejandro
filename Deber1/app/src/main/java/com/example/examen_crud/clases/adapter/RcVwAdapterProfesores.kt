package com.example.examen_crud.clases.adapter

import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.MainActivity
import com.example.examen_crud.R
import com.example.examen_crud.clases.data.Profesor


class RcVwAdapterProfesores(
    private val parentContext: MainActivity,
    private val list: ArrayList<Profesor>,
    private val onClickListener: (profesor: Profesor) -> Unit
): RecyclerView.Adapter<RcVwAdapterProfesores.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val codeProfesorTextView: TextView
        val nombreTextView: TextView
        val fechaInicioTextView: TextView
        val salarioTextView: TextView
        val cantidadAsignaturasTextView: TextView

        init {
            codeProfesorTextView = view.findViewById(R.id.rvw_codeProfesor)
            nombreTextView = view.findViewById(R.id.rvw_nombreProfesor)
            fechaInicioTextView = view.findViewById(R.id.rvw_fechaIncio)
            salarioTextView = view.findViewById(R.id.rvw_salario)
            cantidadAsignaturasTextView = view.findViewById(R.id.rvw_cantidadAsignaturas)

            view.setOnCreateContextMenuListener(this)


            itemView.isClickable = true
            itemView.isLongClickable = true

            val typedValue = TypedValue()
            itemView.context.theme.resolveAttribute(android.R.attr.selectableItemBackground, typedValue, true)
            itemView.setBackgroundResource(typedValue.resourceId)


            itemView.setOnClickListener { // Configuración del clic en el elemento
                onClickListener(list[adapterPosition])
            }
        }

        override fun onCreateContextMenu(menu: ContextMenu?, view: View?, menuInfo: ContextMenu.ContextMenuInfo?) {
            if (menu != null) {
                val inflater = MenuInflater(view?.context)
                inflater.inflate(R.menu.menu_profesor, menu)

                parentContext.setSelectedProfesorCode(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.rvw_profesor,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val profesor = this.list[position]
        holder.codeProfesorTextView.text = profesor.codeProfesor.toString()
        holder.nombreTextView.text = profesor.nombreProfesor
        holder.fechaInicioTextView.text = profesor.fecha_Inicio.toString()
        holder.salarioTextView.text = "$" +profesor.salarioProfesor.toString()
        holder.cantidadAsignaturasTextView.text = profesor.cantidad_asignaturas.toString()
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}