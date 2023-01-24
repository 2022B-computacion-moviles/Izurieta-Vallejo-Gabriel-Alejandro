class Asignatura(
    var alumnosRegistrados: Int,
    var nombreAsignatura: String,
    var horasImpartidas: Double,
    var aulaClase: Char,
    var esVesptertina: Boolean
){
    override fun toString(): String {
        return ("Alumnos registrados: ${alumnosRegistrados}\t--,Nombre de la asignatura:${nombreAsignatura}\t--," +
                "Horas impartidas: ${horasImpartidas}\t--" +
                        ",Aula de clase: ${aulaClase}\t--," +
                "¿Es vespertina (S/N)?${esVesptertina}\t--")
    }
}

