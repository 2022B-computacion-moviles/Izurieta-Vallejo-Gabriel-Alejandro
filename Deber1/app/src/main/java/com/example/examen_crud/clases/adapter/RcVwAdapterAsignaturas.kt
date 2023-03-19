package com.example.examen_crud.clases.adapter

import android.util.TypedValue
import android.view.*
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.examen_crud.ListaAsignatura
import com.example.examen_crud.MainActivity
import com.example.examen_crud.R
import com.example.examen_crud.clases.data.Asignatura
import com.example.examen_crud.clases.data.Profesor


class RcVwAdapterAsignaturas(
    private val parentContext: ListaAsignatura,
    private val list: ArrayList<Asignatura>,
    private val onClickListener: (asignatura: Asignatura) -> Unit
): RecyclerView.Adapter<RcVwAdapterAsignaturas.MyViewHolder>() {
    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnCreateContextMenuListener {
        val nombreAsignaturaTextView: TextView
        val costoMaterialesTextView: TextView
        val esVespertinaTextView: TextView
        val numeroAlumnosTextView: TextView

        init {
            nombreAsignaturaTextView = view.findViewById(R.id.rvw_NombreAsignatura)
            costoMaterialesTextView = view.findViewById(R.id.rvw_costoAsitnatura)
            esVespertinaTextView = view.findViewById(R.id.rvw_esVespertinaAsignatura)
            numeroAlumnosTextView = view.findViewById(R.id.rvw_numAlumnos)

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
                inflater.inflate(R.menu.menu_asignatura, menu)

                parentContext.setSelectedAsignaturaCode(list[adapterPosition])
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.rvw_asignatura,
                parent,
                false
            )

        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val profesor = this.list[position]
        holder.nombreAsignaturaTextView.text = profesor.nombreAsignatura
        holder.costoMaterialesTextView.text = "$" +profesor.costoMateriales.toString()
        holder.esVespertinaTextView.text = profesor.esVespertina.toString()
        holder.numeroAlumnosTextView.text = profesor.numeroAlumnos.toString()
    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}