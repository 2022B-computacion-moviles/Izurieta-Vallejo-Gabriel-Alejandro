import java.awt.PageAttributes.PrintQualityType
import java.util.*

fun main(args: Array<String>) {
    println("Hola mundo")
    //Tipos de variables
    //Inmutables (no re asignar) =
    val inmutable: String = "Vicente";
    //inmutable="Adrian"
    // Mutables (re signar) =
    var mutable:String ="Vicente";
    mutable="Adrian";

    //val > vars
    //Duck typing
    val ejemploVariable:String ="Ejemplo";
    ejemploVariable.trim();
    val edadEjemplo: Int = 12;

    //Variables primitivas
    val nombreProfesor: String ="Gabriel Izurieta"
    val sueldo: Double=1.2;
    val estadoCivil: Char='S';
    val mayorEdad : Boolean = true;
    //Clases
    val fechaNacimiento: Date = Date() //no se usa "new" en clases

    if(true){

    }else{

    }
    //Switch no existe
    val estadoCivilWhen: Char='S'
    when(estadoCivilWhen){
        ('S')->{
            println("Soltero")
        }
        ('C')->{
            print("Casado")
        }
        'V'->{
            print("viudo")
        }
        else->{
            print("Desconocido")
        }
    }
    //If PEQUEÑO --- Característica de Kotlin
    //Si el estadoCivilWhen es si entonces coquetea
    val coqueto= if(estadoCivilWhen=='S') "Si" else "No"


}