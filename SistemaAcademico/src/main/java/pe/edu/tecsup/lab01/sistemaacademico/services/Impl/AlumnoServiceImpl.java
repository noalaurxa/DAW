package pe.edu.tecsup.lab01.sistemaacademico.services.Impl;

import pe.edu.tecsup.lab01.sistemaacademico.dao.AlumnoDAO;
import pe.edu.tecsup.lab01.sistemaacademico.dao.Impl.AlumnoDAOImpl;
import pe.edu.tecsup.lab01.sistemaacademico.model.Alumno;
import pe.edu.tecsup.lab01.sistemaacademico.services.AlumnoService;

import java.util.List;

public class AlumnoServiceImpl implements AlumnoService {

    private AlumnoDAO alumnoDAO = new AlumnoDAOImpl();

    @Override
    public void agregarAlumno(Alumno alumno) {
        alumnoDAO.agregarAlumno(alumno);
    }

    @Override
    public void actualizarAlumno(Alumno alumno) {
        alumnoDAO.actualizarAlumno(alumno);
    }

    @Override
    public void eliminarAlumno(int idAlumno) {
        alumnoDAO.eliminarAlumno(idAlumno);
    }

    @Override
    public Alumno obtenerAlumno(int idAlumno) {
        return alumnoDAO.obtenerAlumno(idAlumno);
    }

    @Override
    public List<Alumno> listarAlumnos() {
        return alumnoDAO.listarAlumnos();
    }
}
