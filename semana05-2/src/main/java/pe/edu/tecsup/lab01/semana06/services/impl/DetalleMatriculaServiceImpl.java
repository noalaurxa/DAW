package pe.edu.tecsup.lab01.semana06.services.impl;

import pe.edu.tecsup.lab01.semana06.dao.DetalleMatriculaDAO;
import pe.edu.tecsup.lab01.semana06.dao.impl.DetalleMatriculaDAOImpl;
import pe.edu.tecsup.lab01.semana06.model.entities.DetalleMatricula;
import pe.edu.tecsup.lab01.semana06.services.DetalleMatriculaService;

import java.util.List;

public class DetalleMatriculaServiceImpl implements DetalleMatriculaService {

    private final DetalleMatriculaDAO detalleMatriculaDAO;

    public DetalleMatriculaServiceImpl() {
        this.detalleMatriculaDAO = new DetalleMatriculaDAOImpl();
    }

    @Override
    public void registrarDetalle(DetalleMatricula detalle) {
        detalleMatriculaDAO.insert(detalle);
    }

    @Override
    public List<DetalleMatricula> listarPorMatricula(int idMatricula) {
        return detalleMatriculaDAO.findByMatricula(idMatricula);
    }
}
