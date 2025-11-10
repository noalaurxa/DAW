package pe.edu.tecsup.lab01.semana06.dao.impl;

import pe.edu.tecsup.lab01.semana06.config.DatabaseConfig;
import pe.edu.tecsup.lab01.semana06.dao.DetalleMatriculaDAO;
import pe.edu.tecsup.lab01.semana06.model.entities.DetalleMatricula;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleMatriculaDAOImpl implements DetalleMatriculaDAO {

    @Override
    public void insert(DetalleMatricula detalle) {
        String sql = "INSERT INTO DetalleMatricula (idMatricula, idCurso, estado) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, detalle.getIdMatricula());
            ps.setInt(2, detalle.getIdCurso());
            ps.setString(3, detalle.getEstado());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    detalle.setIdDetalle(rs.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DetalleMatricula> findByMatricula(int idMatricula) {
        List<DetalleMatricula> lista = new ArrayList<>();
        String sql = "SELECT idDetalle, idMatricula, idCurso, estado FROM DetalleMatricula WHERE idMatricula = ?";
        try (Connection conn = DatabaseConfig.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMatricula);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleMatricula d = new DetalleMatricula(
                            rs.getInt("idDetalle"),
                            rs.getInt("idMatricula"),
                            rs.getInt("idCurso"),
                            rs.getString("estado")
                    );
                    lista.add(d);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
