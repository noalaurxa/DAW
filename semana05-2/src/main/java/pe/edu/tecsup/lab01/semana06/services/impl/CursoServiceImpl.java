package pe.edu.tecsup.lab01.semana06.services.impl;

import pe.edu.tecsup.lab01.semana06.dao.CursoDAO;
import pe.edu.tecsup.lab01.semana06.model.entities.Curso;
import pe.edu.tecsup.lab01.semana06.services.CursoService;

import java.util.List;

public class CursoServiceImpl implements CursoService {

    private final CursoDAO cursoDAO;

    // Inyección del DAO vía constructor
    public CursoServiceImpl(CursoDAO cursoDAO) {
        this.cursoDAO = cursoDAO;
    }

    @Override
    public void registrarCurso(Curso curso) {
        cursoDAO.insert(curso);
    }

    @Override
    public Curso obtenerCurso(int id) {
        return cursoDAO.findById(id);
    }

    @Override
    public List<Curso> listarCursos() {
        return cursoDAO.findAll();
    }
}
