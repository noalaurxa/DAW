package pe.edu.tecsup.lab01.semana06.services;

import pe.edu.tecsup.lab01.semana06.model.entities.DetalleMatricula;
import java.util.List;

public interface DetalleMatriculaService {
    void registrarDetalle(DetalleMatricula detalle);
    List<DetalleMatricula> listarPorMatricula(int idMatricula);
}
