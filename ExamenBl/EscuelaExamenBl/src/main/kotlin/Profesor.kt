class Profesor(
    var materiasImpartidas: Int,
    var nombreProfesor: String,
    var horasLaborables: Double,
    var genero: Char,
    var trabajaTiempoCompleto: Boolean,
    var asignaturas: ArrayList<Asignatura>
){
    override fun toString(): String {
        return ("Materias: ${materiasImpartidas},\t--\tNombre: ${nombreProfesor},\t--\t" +
                "Horas : ${horasLaborables},\t--\t" +
                "Género: ${genero}\t--\t¿Trabaja tiempo completo?${trabajaTiempoCompleto},\t--\t" +
                "Asignaturas: ${asignaturas}")
    }
}