package pe.edu.tecsup.lab01.semana06.dao;

import pe.edu.tecsup.lab01.semana06.model.entities.DetalleMatricula;
import java.util.List;

public interface DetalleMatriculaDAO {
    void insert(DetalleMatricula detalle);
    List<DetalleMatricula> findByMatricula(int idMatricula);
}
