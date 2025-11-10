package pe.edu.tecsup.lab01.semana06.dao.impl;

import pe.edu.tecsup.lab01.semana06.config.DatabaseConfig;
import pe.edu.tecsup.lab01.semana06.dao.PeriodoAcademicoDAO;
import pe.edu.tecsup.lab01.semana06.model.entities.PeriodoAcademico;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodoAcademicoDAOImpl implements PeriodoAcademicoDAO {

    @Override
    public void insert(PeriodoAcademico periodo) {
        String sql = "INSERT INTO PeriodoAcademico (nombrePeriodo, fechaInicio, fechaFin, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, periodo.getNombrePeriodo());
            ps.setDate(2, periodo.getFechaInicio());
            ps.setDate(3, periodo.getFechaFin());
            ps.setString(4, periodo.getEstado());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    periodo.setIdPeriodo(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar periodo académico: " + e.getMessage());
        }
    }

    @Override
    public PeriodoAcademico findById(int id) {
        String sql = "SELECT idPeriodo, nombrePeriodo, fechaInicio, fechaFin, estado FROM PeriodoAcademico WHERE idPeriodo = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new PeriodoAcademico(
                            rs.getInt("idPeriodo"),
                            rs.getString("nombrePeriodo"),
                            rs.getDate("fechaInicio"),
                            rs.getDate("fechaFin"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar periodo académico: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<PeriodoAcademico> findAll() {
        List<PeriodoAcademico> lista = new ArrayList<>();
        String sql = "SELECT idPeriodo, nombrePeriodo, fechaInicio, fechaFin, estado FROM PeriodoAcademico";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PeriodoAcademico p = new PeriodoAcademico(
                        rs.getInt("idPeriodo"),
                        rs.getString("nombrePeriodo"),
                        rs.getDate("fechaInicio"),
                        rs.getDate("fechaFin"),
                        rs.getString("estado")
                );
                lista.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar periodos académicos: " + e.getMessage());
        }
        return lista;
    }
}
