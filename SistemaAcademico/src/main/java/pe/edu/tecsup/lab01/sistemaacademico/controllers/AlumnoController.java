package pe.edu.tecsup.lab01.sistemaacademico.controllers;

import pe.edu.tecsup.lab01.sistemaacademico.model.Alumno;
import pe.edu.tecsup.lab01.sistemaacademico.services.AlumnoService;
import pe.edu.tecsup.lab01.sistemaacademico.services.Impl.AlumnoServiceImpl;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/AlumnoController")
public class AlumnoController extends HttpServlet {

    private final AlumnoService alumnoService = new AlumnoServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "listar";

        try {
            switch (action) {
                case "nuevo":
                    request.getRequestDispatcher("/alumno/agregar.jsp").forward(request, response);
                    break;

                case "editar":
                    int idEditar = Integer.parseInt(request.getParameter("id"));
                    Alumno alumnoEditar = alumnoService.obtenerAlumno(idEditar);
                    request.setAttribute("alumno", alumnoEditar);
                    request.getRequestDispatcher("/alumno/agregar.jsp").forward(request, response);
                    break;

                case "eliminar":
                    int idEliminar = Integer.parseInt(request.getParameter("id"));
                    alumnoService.eliminarAlumno(idEliminar);
                    response.sendRedirect("AlumnoController");
                    break;

                default:
                    List<Alumno> lista = alumnoService.listarAlumnos();
                    request.setAttribute("listaAlumnos", lista);
                    request.getRequestDispatcher("/alumno/listar.jsp").forward(request, response);
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AlumnoController?error=1");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("idAlumno");
            Alumno alumno = new Alumno();
            alumno.setNombre(request.getParameter("nombre"));
            alumno.setApellido(request.getParameter("apellido"));
            alumno.setDni(request.getParameter("dni"));
            alumno.setCorreo(request.getParameter("correo"));
            alumno.setTelefono(request.getParameter("telefono"));

            String fechaStr = request.getParameter("fechaNacimiento");
            if (fechaStr != null && !fechaStr.isEmpty()) {
                alumno.setFechaNacimiento(Date.valueOf(fechaStr));
            }

            if (idStr == null || idStr.isEmpty()) {
                alumnoService.agregarAlumno(alumno);
            } else {
                alumno.setIdAlumno(Integer.parseInt(idStr));
                alumnoService.actualizarAlumno(alumno);
            }

            response.sendRedirect("AlumnoController");
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("AlumnoController?error=1");
        }
    }
}
