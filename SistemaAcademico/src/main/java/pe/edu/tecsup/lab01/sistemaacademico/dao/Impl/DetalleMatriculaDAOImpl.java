package pe.edu.tecsup.lab01.sistemaacademico.dao.Impl;

import pe.edu.tecsup.lab01.sistemaacademico.dao.DetalleMatriculaDAO;
import pe.edu.tecsup.lab01.sistemaacademico.model.DetalleMatricula;
import pe.edu.tecsup.lab01.sistemaacademico.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DetalleMatriculaDAOImpl implements DetalleMatriculaDAO {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    @Override
    public void agregarDetalle(DetalleMatricula detalle) {
        String sql = "INSERT INTO DetalleMatricula(idMatricula, idCurso, estado) VALUES (?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, detalle.getIdMatricula());
            ps.setInt(2, detalle.getIdCurso());
            ps.setString(3, detalle.getEstado());
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public void eliminarDetalle(int idDetalle) {
        String sql = "DELETE FROM DetalleMatricula WHERE idDetalle=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDetalle);
            ps.executeUpdate();
        } catch (SQLException e) { e.printStackTrace(); }
    }

    @Override
    public List<DetalleMatricula> listarDetalles() {
        List<DetalleMatricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM DetalleMatricula";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                DetalleMatricula d = new DetalleMatricula();
                d.setIdDetalle(rs.getInt("idDetalle"));
                d.setIdMatricula(rs.getInt("idMatricula"));
                d.setIdCurso(rs.getInt("idCurso"));
                d.setEstado(rs.getString("estado"));
                lista.add(d);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }

    @Override
    public List<DetalleMatricula> listarDetallesPorMatricula(int idMatricula) {
        List<DetalleMatricula> lista = new ArrayList<>();
        String sql = "SELECT * FROM DetalleMatricula WHERE idMatricula=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idMatricula);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    DetalleMatricula d = new DetalleMatricula();
                    d.setIdDetalle(rs.getInt("idDetalle"));
                    d.setIdMatricula(rs.getInt("idMatricula"));
                    d.setIdCurso(rs.getInt("idCurso"));
                    d.setEstado(rs.getString("estado"));
                    lista.add(d);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
}
