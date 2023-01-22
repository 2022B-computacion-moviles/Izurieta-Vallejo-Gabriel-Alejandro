import java.awt.PageAttributes.PrintQualityType
import java.util.*
import kotlin.collections.ArrayList

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
    val sumaUno = Suma(1,2);
    val sumaDos = Suma(1,null);
    val sumaTres = Suma(null,1)
    val sumaCuatro = Suma(null,null)

    sumaUno.sumar()
    sumaDos.sumar()
    sumaTres.sumar()
    sumaCuatro.sumar()

    println(Suma.historialSumas)

    //ARREGLOS
    //Arreglo Estático

    println("\t\tArreglos")
    val arregloEstatico: Array<Int> = arrayOf(1,2,3)
    var respuestaForEach: Unit = println("Inicio Arreglo Estatico ForEach")
        arregloEstatico
        .forEach {
            valorActual: Int->
            println("Valor actual: ${valorActual}")
    }
    print(respuestaForEach)

    //Arreglo Dinámico
    val arregloDinamico: ArrayList<Int> = arrayListOf(1,2,3,4,5,6,7,8,9,10)
    println(arregloDinamico)
    arregloDinamico.add(11);
    arregloDinamico.add(12);
    println(arregloDinamico)
    //Operadores -> Sirven para los arreglos estáticos y dinámicos
    // For Each -> Unit
    // Iterar uun arreglo
val respuestaForEachDinamico: Unit= println("Inicio Arreglo Dinamico ForEachIndexed")
    arregloDinamico.forEachIndexed{
            indice: Int, valorActual:Int ->
        println("Valor actual: ${valorActual} Indice: ${indice}")
    }
    println(respuestaForEachDinamico);
    //MAP -> Muta el arreglo (Cambia el arreglo)
    // 1) Enviamos el nuevo valor de la iteración
    // 2) Nos devuelve es un Nuevo Arreglo con los valores modificados
    println("\t\tArreglo MAP")
    val respuestaMap: List<Double> = arregloDinamico
        .map {
            valorActual: Int->
            return@map valorActual.toDouble() * 100.00
        }
    println(respuestaMap)
    val respuestaMapDos = println("Map 2 (it+15)")
    arregloDinamico.map { it+15 }
    println(respuestaMapDos)

    // Filter -> FILTRAR EL ARREGLO
    // 1) Devolver una expresión (True o False)
    // 2) Nuevo arreglo filtrado
    println("\t\tARREGLOS FILTRADOS")
    val respuestaFilter: List<Int> =arregloDinamico
        .filter {
            valorActual: Int ->
            val mayoresACinco: Boolean = valorActual>5 //Expresión Condicion
            return@filter mayoresACinco
        }
    println("El arreglo Filtrado es: ${respuestaFilter}")
    val respuestaFilterDos = arregloDinamico.filter { it<5}
    println("El arreglo Filtrado por it<5 es: ${respuestaFilterDos}")

    //OR AND
    //OR -> ANY (Alguno cumple?)
    //AND -> ALL (Todos cumplen?)
    println("\t\t Sentencias AND - OR")
    val respuestaAny: Boolean = arregloDinamico
        .any {
            valorActual: Int ->
            return@any (valorActual > 5)
        }
    println("Con .any el arreglo > 5 queda: ${respuestaAny}")
    val respuestaAnySencilla = arregloDinamico.any { it>5 }
    println("Con .any sencilla el arreglo > 5 queda: ${respuestaAnySencilla}")
    val respuestaAll: Boolean = arregloDinamico
        .all {
            valorActual: Int ->
            return@all (valorActual > 5)
        }
    println("Con .all el arreglo > 5 queda: ${respuestaAll}")
    val respuestaAllSencilla = arregloDinamico.all { it>5 }
    println("Con .all sencilla el arreglo > 5 queda: ${respuestaAllSencilla}")


    val arregloEjemplo: Array<Int> = arrayOf(1,2,3,4,5,6)
    val foreachEjemplo: Unit = arregloEjemplo
        .forEach {
            valorActual: Int ->
            return@forEach println("El valor del arreglo Ejemplo es: ${valorActual}")
        }
    val arregloEjemploDos: Array<Int> = arrayOf(1,2,3,4,5,6,7)
    /*val forEachEjemploDos: Unit = arregloEjemploDos
        .forEachIndexed {
            valorActual: Int , indice: Int->
            val bool: Boolean;
            if (valorActual>=5){
                bool=true;
            }else{
                bool=false;
            }
            return@forEachIndexed println("El resultado es ${bool}")
        }*/
    //REDUCE -> Valor acumulado
    //Valor acumulado = 0 (Siempre 0 en lenguae Kotlin)
    //valorIteracion1 = 1 + 0 = 1 ....
    //valorItracionn = acumulado + n = 15
    println("\t\t REDUCE")
    val respuestaReduce: Int = arregloDinamico
        .reduce { // acumulado = 0 -> SIEMPRE EMPIEZA EN 0
                acumulado: Int, valorActual: Int ->
                return@reduce (acumulado + valorActual)// ->Logica negocio
        }
    println("El resultado del acumulado es: "+respuestaReduce)
    val respuestaReduceSencillo = arregloDinamico
        .reduce { acc, i ->
        return@reduce acc+i}
    println("El resultado del acumulado sencillo es: ${respuestaReduceSencillo}")


            //Fin de la CLASE INICIAL
}


//                  FUNCIONES
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
    protected val numeroDos: Int  //Propiedad
){
    init{//Bloque de codigo del constructor primario
        //this.numeroUno    //"this" opcional
        //this.numeroDos
        numeroUno
        numeroDos
        println("Iniciando")
    }
}


class Suma( //Constructor Primario Suma
    uno: Int, //Parametro
    dos: Int, //Parametro
): Numeros(//Heredamos de la clase Numeros
    //Super constructor numeros
    uno,
    dos
){
    init{//Bloque constructor primario
        this.numeroUno
        this.numeroDos
    }
    constructor(//Segundo Constructor
    uno: Int?,
    dos: Int
    ):this(
        if (uno == null ) 0 else uno,
        dos
    ){}
    constructor(//Tercer Constructor
        uno: Int,
        dos: Int?
    ):this(
        uno,
        if (dos == null ) 0 else dos
    ){}
    constructor(//Cuarto Constructor
        uno: Int?,
        dos: Int?
    ):this(
        if (uno == null ) 0 else uno,
        if (dos == null ) 0 else dos
    ){}
    fun sumar():Int{
        var total = numeroUno + numeroDos;
        agregarHistorial(total);
        return total;
    }

    //El companion object se puede compartir entre todas las instancias de la clase
    companion object {
        val pi = 3.14; //Suma.pi ->3.14
        val historialSumas = arrayListOf<Int>()
        fun agregarHistorial(valorNuevaSuma: Int){
            historialSumas.add(valorNuevaSuma)
        }
    }

}