package pe.edu.tecsup.lab01.semana06.dao.impl;

import pe.edu.tecsup.lab01.semana06.config.DatabaseConfig;
import pe.edu.tecsup.lab01.semana06.dao.CursoDAO;
import pe.edu.tecsup.lab01.semana06.model.entities.Curso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoDAOImpl implements CursoDAO {

    @Override
    public void insert(Curso curso) {
        String sql = "INSERT INTO Curso (nombreCurso, creditos) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, curso.getNombreCurso());
            ps.setInt(2, curso.getCreditos());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    curso.setIdCurso(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al insertar curso: " + e.getMessage());
        }
    }

    @Override
    public Curso findById(int id) {
        String sql = "SELECT idCurso, nombreCurso, creditos FROM Curso WHERE idCurso = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Curso(
                            rs.getInt("idCurso"),
                            rs.getString("nombreCurso"),
                            rs.getInt("creditos")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar curso: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Curso> findAll() {
        List<Curso> lista = new ArrayList<>();
        String sql = "SELECT idCurso, nombreCurso, creditos FROM Curso";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Curso curso = new Curso(
                        rs.getInt("idCurso"),
                        rs.getString("nombreCurso"),
                        rs.getInt("creditos")
                );
                lista.add(curso);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar cursos: " + e.getMessage());
        }
        return lista;
    }
}
