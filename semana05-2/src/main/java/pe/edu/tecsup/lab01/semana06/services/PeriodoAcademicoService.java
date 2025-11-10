package pe.edu.tecsup.lab01.semana06.services;

import pe.edu.tecsup.lab01.semana06.model.entities.PeriodoAcademico;
import java.util.List;

public interface PeriodoAcademicoService {
    PeriodoAcademico obtenerPeriodo(int id);
    List<PeriodoAcademico> listarPeriodos();
}
