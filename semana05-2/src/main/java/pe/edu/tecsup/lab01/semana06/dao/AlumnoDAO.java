package pe.edu.tecsup.lab01.semana06.dao;

import pe.edu.tecsup.lab01.semana06.model.entities.Alumno;
import java.util.List;

public interface AlumnoDAO {
    void insert(Alumno alumno);
    Alumno findById(int id);
    List<Alumno> findAll();
    void update(Alumno alumno);
    void delete(int id);
}
