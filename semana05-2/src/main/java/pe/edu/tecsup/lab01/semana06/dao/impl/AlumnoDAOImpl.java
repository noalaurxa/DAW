package pe.edu.tecsup.lab01.semana06.dao.impl;

import pe.edu.tecsup.lab01.semana06.config.DatabaseConfig;
import pe.edu.tecsup.lab01.semana06.dao.AlumnoDAO;
import pe.edu.tecsup.lab01.semana06.model.entities.Alumno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    @Override
    public void insert(Alumno alumno) {
        String sql = "INSERT INTO Alumno (nombre, apellido) VALUES (?, ?)";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    alumno.setIdAlumno(rs.getInt(1));
                }
            }

        } catch (SQLException e) {
            System.err.println("Error al insertar Alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public Alumno findById(int id) {
        String sql = "SELECT idAlumno, nombre, apellido FROM Alumno WHERE idAlumno = ?";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Alumno a = new Alumno();
                    a.setIdAlumno(rs.getInt("idAlumno"));
                    a.setNombre(rs.getString("nombre"));
                    a.setApellido(rs.getString("apellido"));
                    return a;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar Alumno por ID: " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Alumno> findAll() {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT idAlumno, nombre, apellido FROM Alumno";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Alumno a = new Alumno();
                a.setIdAlumno(rs.getInt("idAlumno"));
                a.setNombre(rs.getString("nombre"));
                a.setApellido(rs.getString("apellido"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.err.println("Error al listar Alumnos: " + e.getMessage());
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public void update(Alumno alumno) {
        String sql = "UPDATE Alumno SET nombre = ?, apellido = ? WHERE idAlumno = ?";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setInt(3, alumno.getIdAlumno());
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al actualizar Alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM Alumno WHERE idAlumno = ?";
        try (Connection c = DatabaseConfig.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al eliminar Alumno: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
