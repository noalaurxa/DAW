package pe.edu.tecsup.lab01.semana06.services.impl;

import pe.edu.tecsup.lab01.semana06.dao.AlumnoDAO;
import pe.edu.tecsup.lab01.semana06.model.entities.Alumno;
import pe.edu.tecsup.lab01.semana06.services.AlumnoService;

import java.util.List;

public class AlumnoServiceImpl implements AlumnoService {

    private final AlumnoDAO alumnoDAO;

    public AlumnoServiceImpl(AlumnoDAO alumnoDAO) {
        this.alumnoDAO = alumnoDAO;
    }

    @Override
    public void registrarAlumno(Alumno alumno) {
        alumnoDAO.insert(alumno);
    }

    @Override
    public Alumno obtenerAlumno(int id) {
        return alumnoDAO.findById(id);
    }

    @Override
    public List<Alumno> listarAlumnos() {
        return alumnoDAO.findAll();
    }

    @Override
    public void actualizarAlumno(Alumno alumno) {
        alumnoDAO.update(alumno);
    }

    @Override
    public void eliminarAlumno(int id) {
        alumnoDAO.delete(id);
    }
}
