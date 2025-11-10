package pe.edu.tecsup.lab01.semana06.dao.impl;

import pe.edu.tecsup.lab01.semana06.config.DatabaseConfig;
import pe.edu.tecsup.lab01.semana06.dao.MatriculaDAO;
import pe.edu.tecsup.lab01.semana06.model.entities.Matricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAOImpl implements MatriculaDAO {

    @Override
    public void insert(Matricula matricula) {
        String sql = "INSERT INTO Matricula (idAlumno, idPeriodo, fechaMatricula, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, matricula.getIdAlumno());
            ps.setInt(2, matricula.getIdPeriodo());
            ps.setDate(3, matricula.getFechaMatricula());
            ps.setString(4, matricula.getEstado());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    matricula.setIdMatricula(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar matrícula: " + e.getMessage());
        }
    }

    @Override
    public void update(Matricula matricula) {
        String sql = "UPDATE Matricula SET idAlumno = ?, idPeriodo = ?, fechaMatricula = ?, estado = ? WHERE idMatricula = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, matricula.getIdAlumno());
            ps.setInt(2, matricula.getIdPeriodo());
            ps.setDate(3, matricula.getFechaMatricula());
            ps.setString(4, matricula.getEstado());
            ps.setInt(5, matricula.getIdMatricula());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar matrícula: " + e.getMessage());
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Matricula WHERE idMatricula = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar matrícula: " + e.getMessage());
        }
    }

    @Override
    public Matricula findById(int id) {
        String sql = "SELECT idMatricula, idAlumno, idPeriodo, fechaMatricula, estado FROM Matricula WHERE idMatricula = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Matricula(
                            rs.getInt("idMatricula"),
                            rs.getInt("idAlumno"),
                            rs.getInt("idPeriodo"),
                            rs.getDate("fechaMatricula"),
                            rs.getString("estado")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar matrícula: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Matricula> findAll() {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT idMatricula, idAlumno, idPeriodo, fechaMatricula, estado FROM Matricula";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Matricula m = new Matricula(
                        rs.getInt("idMatricula"),
                        rs.getInt("idAlumno"),
                        rs.getInt("idPeriodo"),
                        rs.getDate("fechaMatricula"),
                        rs.getString("estado")
                );
                lista.add(m);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar matrículas: " + e.getMessage());
        }
        return lista;
    }
}
