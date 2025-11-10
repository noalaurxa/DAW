package pe.edu.tecsup.lab01.semana06.services.impl;

import pe.edu.tecsup.lab01.semana06.dao.PeriodoAcademicoDAO;
import pe.edu.tecsup.lab01.semana06.model.entities.PeriodoAcademico;
import pe.edu.tecsup.lab01.semana06.services.PeriodoAcademicoService;

import java.util.List;

public class PeriodoAcademicoServiceImpl implements PeriodoAcademicoService {

    private PeriodoAcademicoDAO periodoDAO;

    public PeriodoAcademicoServiceImpl(PeriodoAcademicoDAO periodoDAO) {
        this.periodoDAO = periodoDAO;
    }

    @Override
    public PeriodoAcademico obtenerPeriodo(int id) {
        return periodoDAO.findById(id);
    }

    @Override
    public List<PeriodoAcademico> listarPeriodos() {
        return periodoDAO.findAll();
    }
}
