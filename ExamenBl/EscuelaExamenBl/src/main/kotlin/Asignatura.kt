class Asignatura(
    var alumnosRegistrados: Int,
    var nombreAsignatura: String,
    var horasImpartidas: Double,
    var aulaClase: Char,
    var esVesptertina: Boolean
){
    override fun toString(): String {
        return ("\tAlumnos registrados: ${alumnosRegistrados},\t--\tNombre de la asignatura:${nombreAsignatura},\t--\t" +
                "Horas impartidas: ${horasImpartidas},\t--\t" +
                "Aula de clase: ${aulaClase}\t--\t" +
                "¿Es vespertina (S/N)?${esVesptertina}")
    }
}

