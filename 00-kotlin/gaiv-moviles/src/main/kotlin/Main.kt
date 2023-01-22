import java.awt.PageAttributes.PrintQualityType
import java.util.*

fun main(args: Array<String>) {
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
//No existe el void en kotlin pero si el Unit
fun imprimirNombre(nombre: String): Unit{
    println("Nombre : ${nombre}");
}
fun calcularSueldo(
    sueldo:Double, //requerido
    tasa: Double=12.00,//opcional por defecto
    //Evita los null pointerExceptions
    bonoEspecial: Double?=null // opcional nulo , verificamos que puede ser o no nulo
): Double{
    if(bonoEspecial!=null){
        return sueldo * tasa * bonoEspecial
    }
    return sueldo * tasa
}

//CLASES
abstract class NumerosJava{
    protected val numeroUno:Int
    private val numeroDos: Int
    constructor(
        uno:Int,//Parametro
        dos:Int //Parametro
    ){
        this.numeroUno = uno;
        this.numeroDos = dos;
        println("Iniciando")
    }
}
//CLASES EN KOTLIN
abstract class Numeros(//Constructor Primario
    //uno : Int, //Parametro
    //public var uno : Int, //Propiedad de la clase publica
    protected val numeroUno: Int, //Propiedad
    protected val numeroDos: Int
){
    init{//Bloque de codigo del constructor primario
        //this.numeroUno    //"this" opcional
        //this.numeroDos
        numeroUno
        numeroDos
        println("Iniciando")
    }
}