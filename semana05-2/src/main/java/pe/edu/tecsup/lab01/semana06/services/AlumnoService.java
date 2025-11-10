package pe.edu.tecsup.lab01.semana06.services;

import pe.edu.tecsup.lab01.semana06.model.entities.Alumno;
import java.util.List;

public interface AlumnoService {
    void registrarAlumno(Alumno alumno);
    Alumno obtenerAlumno(int id);
    List<Alumno> listarAlumnos();
    void actualizarAlumno(Alumno alumno);
    void eliminarAlumno(int id);
}
