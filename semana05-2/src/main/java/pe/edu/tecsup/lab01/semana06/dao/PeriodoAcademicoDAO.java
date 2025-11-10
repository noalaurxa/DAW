package pe.edu.tecsup.lab01.semana06.dao;

import pe.edu.tecsup.lab01.semana06.model.entities.PeriodoAcademico;
import java.util.List;

public interface PeriodoAcademicoDAO {
    void insert(PeriodoAcademico periodo);
    PeriodoAcademico findById(int id);
    List<PeriodoAcademico> findAll();
}
