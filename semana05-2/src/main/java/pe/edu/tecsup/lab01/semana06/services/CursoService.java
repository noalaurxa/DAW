package pe.edu.tecsup.lab01.semana06.services;

import pe.edu.tecsup.lab01.semana06.model.entities.Curso;
import java.util.List;

public interface CursoService {
    void registrarCurso(Curso curso);
    Curso obtenerCurso(int id);
    List<Curso> listarCursos();
}
