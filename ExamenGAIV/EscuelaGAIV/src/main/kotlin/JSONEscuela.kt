import java.io.File
import java.io.FileWriter
import java.io.FileReader
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Objects

class JSONEscuela{
    var gson: Gson = Gson()
    val archivo: File = File("baseDatos/escuela.json")

    fun createfiel(){
        FileWriter(archivo).use { it.write("") }
    }

    fun modifyFile(escuela: ArrayList<Profesor>){
        val json = gson.toJson(escuela)
        FileWriter(archivo).use{
            it.write(json,0,json.length)
        }
        println("Archivo Modificado")
    }

    fun addProfesor(profesor: Profesor){
        if (archivo.exists()){
            val jsonInput = FileReader(archivo).use{
                it.readText()
            }
            if (jsonInput ==""){
                println("Archivo está vacio")
                var profesores = arrayListOf<Profesor>()
                var newJSon = gson.toJson(profesores)

                FileWriter(archivo).use {
                    it.write(newJSon,0,newJSon.length)
                }
            } else {
                val tipoClase = object : TypeToken<ArrayList<Profesor>>() {}.type
                val profesores: ArrayList<Profesor> = gson.fromJson(jsonInput,tipoClase)
                profesores.add(profesor)
                val newJSon = gson.toJson(profesores)
                FileWriter(archivo).use{
                    it.write(newJSon,0,newJSon.length)
                }
            }
        } else{
          println("Archivo no existente, se va a crear")
          createfiel()
          var profesores = arrayListOf<Profesor>(profesor)
          val newJSon =  gson.toJson(profesores)
            FileWriter(archivo).use{
                it.write(newJSon,0,newJSon.length)
            }
        }
        println("Archivo modificado o creado ...")
    }

    fun  readProfesores(): ArrayList<Profesor>{
        var profesores = arrayListOf<Profesor>()
            if(archivo.exists()){
                val input = FileReader(archivo).use{
                    it.readText()
                }
                if(input == ""){
                    profesores = arrayListOf<Profesor>()
                } else {
                    val tipoClase = object : TypeToken<ArrayList<Profesor>>() {}.type
                    var jsonProfesores: ArrayList<Profesor> = gson.fromJson(input,tipoClase)
                    profesores = jsonProfesores
                }
            } else {
                profesores = arrayListOf<Profesor>()
            }
        return profesores
    }
}