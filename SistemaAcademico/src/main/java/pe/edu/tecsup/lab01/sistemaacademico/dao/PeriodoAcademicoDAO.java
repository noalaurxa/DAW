package pe.edu.tecsup.lab01.sistemaacademico.dao;

import pe.edu.tecsup.lab01.sistemaacademico.model.PeriodoAcademico;
import java.util.List;

public interface PeriodoAcademicoDAO {
    void agregarPeriodo(PeriodoAcademico periodo);
    void actualizarPeriodo(PeriodoAcademico periodo);
    void eliminarPeriodo(int idPeriodo);
    PeriodoAcademico obtenerPeriodo(int idPeriodo);
    List<PeriodoAcademico> listarPeriodos();
}
