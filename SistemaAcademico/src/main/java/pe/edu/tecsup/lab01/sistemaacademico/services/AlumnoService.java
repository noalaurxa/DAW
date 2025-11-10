package pe.edu.tecsup.lab01.sistemaacademico.services;

import pe.edu.tecsup.lab01.sistemaacademico.model.Alumno;
import java.util.List;

public interface AlumnoService {
    void agregarAlumno(Alumno alumno);
    void actualizarAlumno(Alumno alumno);
    void eliminarAlumno(int idAlumno);
    Alumno obtenerAlumno(int idAlumno);
    List<Alumno> listarAlumnos();
}
