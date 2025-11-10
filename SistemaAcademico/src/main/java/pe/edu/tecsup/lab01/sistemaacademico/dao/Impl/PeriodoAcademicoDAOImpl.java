package pe.edu.tecsup.lab01.sistemaacademico.dao.Impl;

import pe.edu.tecsup.lab01.sistemaacademico.dao.PeriodoAcademicoDAO;
import pe.edu.tecsup.lab01.sistemaacademico.model.PeriodoAcademico;
import pe.edu.tecsup.lab01.sistemaacademico.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodoAcademicoDAOImpl implements PeriodoAcademicoDAO {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    @Override
    public void agregarPeriodo(PeriodoAcademico periodo) {
        String sql = "INSERT INTO PeriodoAcademico(nombrePeriodo, fechaInicio, fechaFin, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, periodo.getNombrePeriodo());
            ps.setDate(2, new java.sql.Date(periodo.getFechaInicio().getTime()));
            ps.setDate(3, new java.sql.Date(periodo.getFechaFin().getTime()));
            ps.setString(4, periodo.getEstado());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarPeriodo(PeriodoAcademico periodo) {
        String sql = "UPDATE PeriodoAcademico SET nombrePeriodo=?, fechaInicio=?, fechaFin=?, estado=? WHERE idPeriodo=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, periodo.getNombrePeriodo());
            ps.setDate(2, new java.sql.Date(periodo.getFechaInicio().getTime()));
            ps.setDate(3, new java.sql.Date(periodo.getFechaFin().getTime()));
            ps.setString(4, periodo.getEstado());
            ps.setInt(5, periodo.getIdPeriodo());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarPeriodo(int idPeriodo) {
        String sql = "DELETE FROM PeriodoAcademico WHERE idPeriodo=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPeriodo);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PeriodoAcademico obtenerPeriodo(int idPeriodo) {
        String sql = "SELECT * FROM PeriodoAcademico WHERE idPeriodo=?";
        PeriodoAcademico periodo = null;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idPeriodo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    periodo = new PeriodoAcademico();
                    periodo.setIdPeriodo(rs.getInt("idPeriodo"));
                    periodo.setNombrePeriodo(rs.getString("nombrePeriodo"));
                    periodo.setFechaInicio(rs.getDate("fechaInicio"));
                    periodo.setFechaFin(rs.getDate("fechaFin"));
                    periodo.setEstado(rs.getString("estado"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return periodo;
    }

    @Override
    public List<PeriodoAcademico> listarPeriodos() {
        List<PeriodoAcademico> lista = new ArrayList<>();
        String sql = "SELECT * FROM PeriodoAcademico";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                PeriodoAcademico p = new PeriodoAcademico();
                p.setIdPeriodo(rs.getInt("idPeriodo"));
                p.setNombrePeriodo(rs.getString("nombrePeriodo"));
                p.setFechaInicio(rs.getDate("fechaInicio"));
                p.setFechaFin(rs.getDate("fechaFin"));
                p.setEstado(rs.getString("estado"));
                lista.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return lista;
    }
}
