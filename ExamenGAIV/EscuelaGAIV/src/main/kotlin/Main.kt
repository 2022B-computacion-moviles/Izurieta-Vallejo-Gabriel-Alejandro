import java.io.BufferedReader
import java.io.InputStreamReader
import java.security.KeyStore.TrustedCertificateEntry
import java.util.*

val javaReader = BufferedReader(InputStreamReader(System.`in`))
val javaHelper = JSONEscuela()

val stringProfesor : String = "Materias impartidas\tNombre del Profesor\tHoras laborables\tGénero\tTrabaja tiempo Completo\tAsignaturas"
val stringAsignatura : String = "Alumnos registrados\tNombre de la Asignatura\tHoras impartidas\tAula\t¿Es vespertina?"
fun main(args: Array<String>) {
    var CRUDoption : Int
    var salida: Boolean=false
    do {
        CRUDoption = mostrarMenu()
        when (CRUDoption){
            1 ->  {
              var opCreate : Boolean = true
              while (opCreate){
                  create()
                  opCreate=cambiarOpcion()
              }
            }
            2 -> {
                var opRead: Boolean = true
                while (opRead){
                    read()
                    opRead = cambiarOpcion()
                }
            }
            3 -> {
                var opUpdate: Boolean = true
                while (opUpdate){
                    update()
                    opUpdate=cambiarOpcion()
                }
            }
            4 -> {
                var opDelete : Boolean = true
                while (opDelete){
                    delete()
                    opDelete=cambiarOpcion()
                }
            }
            5 -> {
                println("Se va a cerrar el programa CRUD")
                System.exit(0)
            }
            else ->{
                print("Error: La opción ingresada es inválida")
            }
        }
    }while (!salida)
}

//INICIO FUNCIONES
fun mostrarMenu():Int{
    println("Menú de Opciones CRUD");
    println("\tSeleccione la opción requerida: ");
    println("\t\t1) Create");
    println("\t\t2) Read");
    println("\t\t3) Update");
    println("\t\t4) Delete");
    println("\t\t5) Cerrar");
    return javaReader.readLine().toInt()
}

fun cambiarOpcion(): Boolean{
    println("\n\t¿Desea regresar al menú genera? (S/N)")
    return javaReader.readLine().toUpperCase()=="N"
}

fun create(){
    var asignaturas = arrayListOf<Asignatura>()
    var opcion: Boolean;
    println("\t\t***HISTORIAL DE LA Escuela***");
    println("\tRegistro de Profesores");
    print("Ingrese el número de materias (Int): ")
    var materiasProfesor: Int = javaReader.readLine().toInt();
    print("Ingrese el nombre del profesor (String): ")
    var nombreProfesor: String = javaReader.readLine().toString();
    print("Ingrese el núero de materias (Double): ")
    var horasLaborablesProfesor: Double = javaReader.readLine().toDouble();
    print("Ingrese el género del profesor (Char): ")
    var generoProfesor: Char = javaReader.readLine().get(0);
    print("¿El contrato es de tiempo completo? (Boolean): ")
    var tiempoCompletoProfesor: Boolean = javaReader.readLine().toUpperCase()=="S";
    println("")
    //Asignaturas
    print("\n¿Desea impartir más asignaturas? (S/N)")
    opcion = javaReader.readLine().toUpperCase()=="S"
        while (opcion){
            //Registro Asignaturas
            print("\tIngrese el núemro de alumnos registrados (Int): ");
            var alumRegisAsignatura : Int = javaReader.readLine().toInt()
            print("\tIngrese el nombre de la asignatura (String): ");
            var nombreAsignatura : String = javaReader.readLine().toString()
            print("\tIngrese el núemro de alumnos registrados (Double): ");
            var horasImpartidasAsignatura : Double = javaReader.readLine().toDouble()
            print("\tIngrese el núemro de alumnos registrados (Char): ");
            var aulaAsignatura : Char = javaReader.readLine().get(0)
            print("\tIngrese el núemro de alumnos registrados (Boolean): ");
            var esVespertinaAsignatura : Boolean = javaReader.readLine().toUpperCase()=="S"
            asignaturas.add(Asignatura(alumRegisAsignatura,stringAsignatura,horasImpartidasAsignatura,aulaAsignatura,esVespertinaAsignatura))
            print("\t¿Desea ingresasr una nueva asignatura para el respectivo Maestro? (S/N)")
            opcion=javaReader.readLine().toUpperCase()=="S"
        }
    javaHelper.addProfesor(Profesor(materiasProfesor
        ,nombreProfesor,horasLaborablesProfesor,generoProfesor,tiempoCompletoProfesor,asignaturas))
}
//Funcion READ
fun read(){
    var JSONProfesores = javaHelper.readProfesores()
    println("\tSeleccione una opción en read() (Int): ")
    println("1. Mostrar a los Profesores")
    println("2. Mostrar un solo Profesor")
    print("Opción escogida: ")
    var opcion = javaReader.readLine().toInt()
    when(opcion){
        1 -> {
            println(stringProfesor)
            for (profesor in JSONProfesores){
                print(profesor.toString())
                println("")
            }
        }
        2 -> {
            var opcionSelecProfesor : Boolean = true
            while (opcionSelecProfesor){
                for(profesor in JSONProfesores){
                    println("${profesor.nombreProfesor} - ${profesor.asignaturas}")
                }
                print("Ingrese el numero de materias del profesor para mostrar su información")
                var idSearch : Int = javaReader.readLine().toInt()
                println(stringProfesor)
                if (JSONProfesores.any {it.materiasImpartidas == idSearch}){
                    println(JSONProfesores
                        .filter{ it.materiasImpartidas == idSearch }.toString())
                }
                println("")
                print("¿Desea realizar más consultas (S/N)?")
                opcionSelecProfesor = javaReader.readLine().toUpperCase()=="S"
            }
        }
        else -> {
            println("ERROR: No existe esa opción en el menú de Profesores")
        }
    }
}

fun atributeUpdater(atributo:String, esProfesor: Boolean): Boolean{
    if(esProfesor){
        print("¿Actualizar $atributo del profesor (S/N)? ")
    } else {
        print("¿Actualizar $atributo de la asignatura (S/N)? ")
    }
    return javaReader.readLine().toUpperCase()=="S"
}

fun update() {
    var JSONProfesores = javaHelper.readProfesores()
    println("\t\tSeleccione la opción en update() (Int)")
    println("\t1) Actualizar Profesor")
    println("\t2) Actualizar Asignaturas del Profesor")
    print("Opción escogida: ")
    var opcion: Int = javaReader.readLine().toInt();
    when (opcion) {
        1 -> {
            var opcionUpdateProfesor: Boolean = true
            while (opcionUpdateProfesor) {
                for (profesor in JSONProfesores) {
                    println("${profesor.nombreProfesor} : ${profesor.asignaturas}")
                }
                print("Ingrese el numero de materias del profesor a actualizar: ")
                var numAsignaturaSearch: Int = javaReader.readLine().toInt()
                if (JSONProfesores.any { it.materiasImpartidas==numAsignaturaSearch}) {
                    var profesorTemp = JSONProfesores
                        .filter { it.materiasImpartidas==numAsignaturaSearch}.firstOrNull()

                    if (atributeUpdater("Nombre del profesor", true)) {
                        print("Ingrese el nuevo nombre del profesor: ")
                        profesorTemp!!.nombreProfesor = javaReader.readLine().toString()
                    }
                    if (atributeUpdater("Materias impartidas", true)) {
                        print("Ingrese las materias impartidas del profesor: ")
                        profesorTemp!!.materiasImpartidas = javaReader.readLine().toInt()
                    }
                    if (atributeUpdater("Horas laborables", true)) {
                        print("Ingrese las horas laborables del profesor: ")
                        profesorTemp!!.horasLaborables = javaReader.readLine().toDouble()
                    }
                    if (atributeUpdater("Género", true)) {
                        print("Ingrese el género del profesor: ")
                        profesorTemp!!.genero = javaReader.readLine().get(0)
                    }
                    if (atributeUpdater("¿Trabaja tiempo completo?", true)) {
                        print("¿Trabaja tiempo completo el profesor? ")
                        profesorTemp!!.trabajaTiempoCompleto = javaReader.readLine().toUpperCase() == "S"
                    }

                    // *- identificador de 1->n  -*
                    var oldProfesor = JSONProfesores.filter { it.materiasImpartidas==numAsignaturaSearch}.firstOrNull()
                    val indexReplace = JSONProfesores.indexOf(oldProfesor)
                    if (indexReplace > 0) {
                        JSONProfesores.set(indexReplace, profesorTemp!!)
                    }
                    javaHelper.modifyFile(JSONProfesores)
                } else {
                    println("Ingrese un identificador existente")
                }
                println("¿Desea Continuar? (S/N): ")
                opcionUpdateProfesor = javaReader.readLine().toUpperCase()=="S"
            }
        }
        2 -> {
            for (profesor in JSONProfesores){
                println("${profesor.nombreProfesor}: ${profesor.asignaturas}")
            }
            println("Ingrese el numero de estudiantes en la asignaturas a actualizar: ")
            var idSearch: Int = javaReader.readLine().toInt()
            // *- identificador de 1->n  -*
            if (JSONProfesores.any {it.materiasImpartidas==idSearch}){
                var profesorTemp = JSONProfesores.filter {it.materiasImpartidas==idSearch}.firstOrNull()
                var optionUpdateAsignatura: Boolean = true
                while (optionUpdateAsignatura){
                    for(asignatura in profesorTemp!!.asignaturas){
                        println(asignatura.toString())
                        println()
                    }

                    print("Ingrese el numero de alumnos registrados en la materia a modificar: ")
                    var oldIDAsignatura : Int = javaReader.readLine().toInt()
                    if(oldIDAsignatura > 0){
                        var asignaturaTemp = profesorTemp.asignaturas
                            .filter {it.alumnosRegistrados==idSearch}.firstOrNull()
                            if (atributeUpdater("Alumnos registrados: ", false)){
                                print("Ingrese los alumnos registrados. ")
                                asignaturaTemp!!.alumnosRegistrados = javaReader.readLine().toInt()
                            }

                        var oldAsignatura = profesorTemp.asignaturas.filter { it.alumnosRegistrados==oldIDAsignatura }.firstOrNull()
                        val indexReplace = profesorTemp.asignaturas.indexOf(oldAsignatura)
                        if(profesorTemp.asignaturas.any{it.alumnosRegistrados==oldIDAsignatura}){
                            profesorTemp.asignaturas.set(indexReplace,asignaturaTemp!!)
                        }
                        var oldProfesor = JSONProfesores.filter { it.materiasImpartidas==idSearch}.firstOrNull()
                        val indexReplaceProfesor = JSONProfesores.indexOf(oldProfesor)
                        if (indexReplace>0){
                            JSONProfesores.set(indexReplaceProfesor,profesorTemp!!)
                        }
                        javaHelper.modifyFile(JSONProfesores)
                    }
                    print("\t¿Desea cibtubyark? (S/N): ")
                    optionUpdateAsignatura = javaReader.readLine().toUpperCase()=="S"
                }
            }
            else{
                println("Error: La selección de asignatura no existe, pruebe otra")
            }
        }
        else ->{
            println("Error: Ingrese una opción valida (Int)")
        }
    }
}

fun delete(){
    var JSONProfesores = javaHelper.readProfesores()
    println("\t\tSeleccione una opción de delete() (Int)")
    println("\t1) Eliminar Profesor")
    println("\t2) Eliminar Asignaturas de un Profesor")
    println("\t)3 Eliminar Todo el registro de la escula")
    print("Opción: ")
    val opcion: Int = javaReader.readLine().toInt()
    when (opcion){
        1-> {
            var opcionDeleteProfesor : Boolean = true
            for(profesor in JSONProfesores){
                println("Nombre: ${profesor.nombreProfesor}-- Materias impartidas${profesor.materiasImpartidas}")
            }
            print("Ingrese las materias impartidas del Profesor a eliminar: ")
            var idProfeDelete: Int = javaReader.readLine().toInt()
            if(idProfeDelete > 0){
                var oldProfesor = JSONProfesores.filter { it.materiasImpartidas == idProfeDelete }.firstOrNull()
                val indexProfDelete = JSONProfesores.indexOf(oldProfesor)
                if(indexProfDelete > 0){
                    JSONProfesores.removeAt(indexProfDelete)
                    javaHelper.modifyFile(JSONProfesores)
                }
            }
            print("\t¿Desea continuar? (S/N): ")
            opcionDeleteProfesor = javaReader.readLine().toUpperCase()=="S"
        }

        2-> {
            for (profesor in JSONProfesores){
                println("Nombre: ${profesor.nombreProfesor} -- Materias impartidas${profesor.materiasImpartidas}")
            }
            print("Ingrese el numero de materias impartidas del profesor con las asignaturas a eliminar: ")
            var idProfDelete = javaReader.readLine().toInt()
            if(JSONProfesores.any { it.materiasImpartidas==idProfDelete }){
                var profeTemp = JSONProfesores.filter { it.materiasImpartidas==idProfDelete }.firstOrNull()
                val indexProfEdited = JSONProfesores.indexOf(profeTemp)
                var opcionDeleteAsignatura: Boolean = true;
                while(opcionDeleteAsignatura){
                    for (asignatura in profeTemp!!.asignaturas){
                        println(asignatura.toString())
                    }
                    println("Seleccione una opción (Int): ")
                    println("1) Eliminar una asignatura en especifico por el número de Alumnos registrados")
                    println("2) Eliminar todas las asignaturas de este Profesor")
                    println("")
                    var opcionDeleteAsignaturaByNumAlum : Int = javaReader.readLine().toInt()
                    when(opcionDeleteAsignaturaByNumAlum){
                        1 -> {
                            print("Ingrese el número de alumnos de la asignatura a eliminar: ")
                            var idAsigDelete : Int = javaReader.readLine().toInt()
                            if (profeTemp.asignaturas.any { it.alumnosRegistrados == idAsigDelete }){
                                var erasedAsignatura = profeTemp.asignaturas.filter { it.alumnosRegistrados==idAsigDelete }.firstOrNull()
                                val indexDeleteAsignatura = profeTemp.asignaturas.indexOf(erasedAsignatura)
                                profeTemp.asignaturas.removeAt(indexDeleteAsignatura)
                                JSONProfesores.set(indexProfEdited, profeTemp!!)
                                javaHelper.modifyFile(JSONProfesores)
                            }
                        }
                        2 -> {
                            print("¿Está seguro que desea eliminar estas asignaturas (S/N)? ")
                            if (javaReader.readLine().toUpperCase()=="S"){
                                profeTemp.asignaturas.clear()
                                JSONProfesores.set(indexProfEdited, profeTemp)
                                println("Se eliminaron las asignaturas de este profesor")
                                javaHelper.modifyFile(JSONProfesores)
                            }
                        }
                        3 -> {
                            println("Error: La opción seleccionada es erronea.")
                        }
                    }
                    print("\t¿Desea continuarl (S/N)? ")
                    opcionDeleteAsignatura = javaReader.readLine().toUpperCase()=="S"
                }
            }
            else {
                println("Ingrese un valor valido.")
            }
        }
        3 -> {
            print("¿Desea eliminar todos los registros de la escual (S/N )? ")
            if (javaReader.readLine().toUpperCase()=="S"){
                JSONProfesores.clear()
                println("Se han eliminado todos los registros de la Escuela (Profesor -> Asignaturas)")
                javaHelper.modifyFile(JSONProfesores)
            }
        }
        else ->{
            println("Error: Ingrese una opción válida.")
        }
    }
}