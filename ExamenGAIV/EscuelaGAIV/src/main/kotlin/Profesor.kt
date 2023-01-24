class Profesor(
    var materiasImpartidas: Int,
    var nombreProfesor: String,
    var horasLaborables: Double,
    var genero: Char,
    var trabajaTiempoCompleto: Boolean,
    var asignaturas: ArrayList<Asignatura>
){
    override fun toString(): String {
        return ("Materias impartidas: ${materiasImpartidas}\t--,Nombre del profesor: ${nombreProfesor}\t--," +
                "Horas laborables: ${horasLaborables}\t--" +
                ",Género: ${genero}\t--,¿Trabaja tiempo completo?${trabajaTiempoCompleto}\t--," +
                "Asignaturas: ${asignaturas}\t--")
    }
}