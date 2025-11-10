package pe.edu.tecsup.lab01.semana06.dao;

import pe.edu.tecsup.lab01.semana06.model.entities.Curso;
import java.util.List;

public interface CursoDAO {
    void insert(Curso curso);
    Curso findById(int id);
    List<Curso> findAll();
}
