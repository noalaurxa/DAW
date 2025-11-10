package pe.edu.tecsup.lab01.sistemaacademico.dao;

import pe.edu.tecsup.lab01.sistemaacademico.model.DetalleMatricula;
import java.util.List;

public interface DetalleMatriculaDAO {
    void agregarDetalle(DetalleMatricula detalle);
    void eliminarDetalle(int idDetalle);

    List<DetalleMatricula> listarDetalles();

    List<DetalleMatricula> listarDetallesPorMatricula(int idMatricula);
}
