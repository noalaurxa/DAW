package pe.edu.tecsup.lab01.sistemaacademico.dao;

import pe.edu.tecsup.lab01.sistemaacademico.model.Alumno;
import java.util.List;

public interface AlumnoDAO {
    void agregarAlumno(Alumno alumno);
    void actualizarAlumno(Alumno alumno);
    void eliminarAlumno(int idAlumno);
    Alumno obtenerAlumno(int idAlumno);
    List<Alumno> listarAlumnos();
}
