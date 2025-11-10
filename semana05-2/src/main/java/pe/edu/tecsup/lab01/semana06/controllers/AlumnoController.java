package pe.edu.tecsup.lab01.semana06.controllers;

import pe.edu.tecsup.lab01.semana06.dao.impl.AlumnoDAOImpl;
import pe.edu.tecsup.lab01.semana06.model.entities.Alumno;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/alumnos")
public class AlumnoController extends HttpServlet {

    private AlumnoDAOImpl dao = new AlumnoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                request.getRequestDispatcher("alumnos.jsp?action=add").forward(request, response);
                break;
            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Alumno alumnoEdit = dao.findById(idEdit);
                request.setAttribute("alumno", alumnoEdit);
                request.getRequestDispatcher("alumnos.jsp?action=edit").forward(request, response);
                break;
            case "delete":
                int idDelete = Integer.parseInt(request.getParameter("id"));
                dao.delete(idDelete);
                response.sendRedirect("alumnos");
                break;
            default: // list
                List<Alumno> lista = dao.findAll();
                request.setAttribute("alumnos", lista);
                request.getRequestDispatcher("alumnos.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");

        if (idStr == null || idStr.isEmpty()) {
            // Nuevo alumno
            Alumno a = new Alumno();
            a.setNombre(nombre);
            a.setApellido(apellido);
            dao.insert(a);
        } else {
            // Actualizar alumno
            Alumno a = new Alumno();
            a.setIdAlumno(Integer.parseInt(idStr));
            a.setNombre(nombre);
            a.setApellido(apellido);
            dao.update(a);
        }
        response.sendRedirect("alumnos");
    }
}
