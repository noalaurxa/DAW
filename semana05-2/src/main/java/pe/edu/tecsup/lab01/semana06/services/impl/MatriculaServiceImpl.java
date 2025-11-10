package pe.edu.tecsup.lab01.semana06.services.impl;

import pe.edu.tecsup.lab01.semana06.dao.MatriculaDAO;
import pe.edu.tecsup.lab01.semana06.model.entities.Matricula;
import pe.edu.tecsup.lab01.semana06.services.MatriculaService;

import java.util.List;

public class MatriculaServiceImpl implements MatriculaService {

    private final MatriculaDAO matriculaDAO;

    public MatriculaServiceImpl(MatriculaDAO matriculaDAO) {
        this.matriculaDAO = matriculaDAO;
    }

    @Override
    public void registrarMatricula(Matricula matricula) {
        matriculaDAO.insert(matricula); // Cambiado de save() a insert()
    }

    @Override
    public void actualizarMatricula(Matricula matricula) {
        matriculaDAO.update(matricula);
    }

    @Override
    public void eliminarMatricula(int id) {
        matriculaDAO.delete(id);
    }

    @Override
    public Matricula obtenerMatricula(int id) {
        return matriculaDAO.findById(id);
    }

    @Override
    public List<Matricula> listarMatriculas() {
        return matriculaDAO.findAll();
    }
}
