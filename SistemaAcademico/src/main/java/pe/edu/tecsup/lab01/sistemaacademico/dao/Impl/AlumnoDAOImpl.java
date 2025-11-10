package pe.edu.tecsup.lab01.sistemaacademico.dao.Impl;

import pe.edu.tecsup.lab01.sistemaacademico.dao.AlumnoDAO;
import pe.edu.tecsup.lab01.sistemaacademico.model.Alumno;
import pe.edu.tecsup.lab01.sistemaacademico.util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements AlumnoDAO {

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Connection getConnection() throws SQLException {
        return DBConnection.getConnection();
    }

    @Override
    public void agregarAlumno(Alumno alumno) {
        String sql = "INSERT INTO Alumno(nombre, apellido, dni, correo, telefono, fechaNacimiento) VALUES(?,?,?,?,?,?)";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getDni());
            ps.setString(4, alumno.getCorreo());
            ps.setString(5, alumno.getTelefono());
            ps.setDate(6, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizarAlumno(Alumno alumno) {
        String sql = "UPDATE Alumno SET nombre=?, apellido=?, dni=?, correo=?, telefono=?, fechaNacimiento=? WHERE idAlumno=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, alumno.getNombre());
            ps.setString(2, alumno.getApellido());
            ps.setString(3, alumno.getDni());
            ps.setString(4, alumno.getCorreo());
            ps.setString(5, alumno.getTelefono());
            ps.setDate(6, new java.sql.Date(alumno.getFechaNacimiento().getTime()));
            ps.setInt(7, alumno.getIdAlumno());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarAlumno(int idAlumno) {
        String sql = "DELETE FROM Alumno WHERE idAlumno=?";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAlumno);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Alumno obtenerAlumno(int idAlumno) {
        String sql = "SELECT * FROM Alumno WHERE idAlumno=?";
        Alumno alumno = null;
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idAlumno);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    alumno = new Alumno();
                    alumno.setIdAlumno(rs.getInt("idAlumno"));
                    alumno.setNombre(rs.getString("nombre"));
                    alumno.setApellido(rs.getString("apellido"));
                    alumno.setDni(rs.getString("dni"));
                    alumno.setCorreo(rs.getString("correo"));
                    alumno.setTelefono(rs.getString("telefono"));
                    alumno.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alumno;
    }

    @Override
    public List<Alumno> listarAlumnos() {
        List<Alumno> lista = new ArrayList<>();
        String sql = "SELECT * FROM Alumno";
        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Alumno alumno = new Alumno();
                alumno.setIdAlumno(rs.getInt("idAlumno"));
                alumno.setNombre(rs.getString("nombre"));
                alumno.setApellido(rs.getString("apellido"));
                alumno.setDni(rs.getString("dni"));
                alumno.setCorreo(rs.getString("correo"));
                alumno.setTelefono(rs.getString("telefono"));
                alumno.setFechaNacimiento(rs.getDate("fechaNacimiento"));
                lista.add(alumno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
}
