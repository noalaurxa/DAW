package pe.edu.tecsup.lab01.sistemaacademico.dao.Impl;

import pe.edu.tecsup.lab01.sistemaacademico.dao.MatriculaDAO;
import pe.edu.tecsup.lab01.sistemaacademico.model.Matricula;
import pe.edu.tecsup.lab01.sistemaacademico.model.Alumno;
import pe.edu.tecsup.lab01.sistemaacademico.model.PeriodoAcademico;
import pe.edu.tecsup.lab01.sistemaacademico.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MatriculaDAOImpl implements MatriculaDAO {

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    @Override
    public void agregarMatricula(Matricula m) {
        String sql = "INSERT INTO Matricula(idAlumno, idPeriodo, fechaMatricula, estado) VALUES (?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getIdAlumno());
            ps.setInt(2, m.getIdPeriodo());
            ps.setDate(3, new java.sql.Date(m.getFechaMatricula().getTime()));
            ps.setString(4, m.getEstado());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarMatricula(Matricula m) {
        String sql = "UPDATE Matricula SET idAlumno=?, idPeriodo=?, fechaMatricula=?, estado=? WHERE idMatricula=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, m.getIdAlumno());
            ps.setInt(2, m.getIdPeriodo());
            ps.setDate(3, new java.sql.Date(m.getFechaMatricula().getTime()));
            ps.setString(4, m.getEstado());
            ps.setInt(5, m.getIdMatricula());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarMatricula(int idMatricula) {
        String sql = "DELETE FROM Matricula WHERE idMatricula=?";
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMatricula);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Matricula obtenerMatricula(int idMatricula) {
        String sql = "SELECT m.idMatricula, m.idAlumno, m.idPeriodo, m.fechaMatricula, m.estado, " +
                "a.nombre AS nombreAlumno, p.nombrePeriodo " +
                "FROM Matricula m " +
                "JOIN Alumno a ON m.idAlumno = a.idAlumno " +
                "JOIN PeriodoAcademico p ON m.idPeriodo = p.idPeriodo " +
                "WHERE m.idMatricula = ?";

        Matricula m = null;

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, idMatricula);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    m = new Matricula();
                    m.setIdMatricula(rs.getInt("idMatricula"));
                    m.setIdAlumno(rs.getInt("idAlumno"));
                    m.setIdPeriodo(rs.getInt("idPeriodo"));
                    m.setFechaMatricula(rs.getDate("fechaMatricula"));
                    m.setEstado(rs.getString("estado"));

                    Alumno a = new Alumno();
                    a.setIdAlumno(rs.getInt("idAlumno"));
                    a.setNombre(rs.getString("nombreAlumno"));
                    m.setAlumno(a);

                    PeriodoAcademico p = new PeriodoAcademico();
                    p.setIdPeriodo(rs.getInt("idPeriodo"));
                    p.setNombrePeriodo(rs.getString("nombrePeriodo"));
                    m.setPeriodo(p);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public List<Matricula> listarMatriculas() {
        List<Matricula> lista = new ArrayList<>();
        String sql = "SELECT m.idMatricula, m.fechaMatricula, m.estado, " +
                "a.idAlumno, a.nombre AS nombreAlumno, " +
                "p.idPeriodo, p.nombrePeriodo " +
                "FROM Matricula m " +
                "JOIN Alumno a ON m.idAlumno = a.idAlumno " +
                "JOIN PeriodoAcademico p ON m.idPeriodo = p.idPeriodo " +
                "ORDER BY m.idMatricula";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Matricula m = new Matricula();
                m.setIdMatricula(rs.getInt("idMatricula"));
                m.setFechaMatricula(rs.getDate("fechaMatricula"));
                m.setEstado(rs.getString("estado"));

                Alumno a = new Alumno();
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setNombre(rs.getString("nombreAlumno")); // nombre del JOIN
                m.setAlumno(a);

                PeriodoAcademico p = new PeriodoAcademico();
                p.setIdPeriodo(rs.getInt("idPeriodo"));
                p.setNombrePeriodo(rs.getString("nombrePeriodo")); // nombre del JOIN
                m.setPeriodo(p);

                lista.add(m);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
