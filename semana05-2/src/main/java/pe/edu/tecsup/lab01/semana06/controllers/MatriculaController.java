package pe.edu.tecsup.lab01.semana06.controllers;

import pe.edu.tecsup.lab01.semana06.dao.impl.MatriculaDAOImpl;
import pe.edu.tecsup.lab01.semana06.model.entities.Matricula;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/matriculas")
public class MatriculaController extends HttpServlet {

    private MatriculaDAOImpl dao = new MatriculaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");
        if (action == null) action = "list";

        switch (action) {
            case "add":
                request.getRequestDispatcher("matriculas.jsp?action=add").forward(request, response);
                break;
            case "edit":
                int idEdit = Integer.parseInt(request.getParameter("id"));
                Matricula matriculaEdit = dao.findById(idEdit);
                request.setAttribute("matricula", matriculaEdit);
                request.getRequestDispatcher("matriculas.jsp?action=edit").forward(request, response);
                break;
            case "delete":
                int idDelete = Integer.parseInt(request.getParameter("id"));
                dao.delete(idDelete);
                response.sendRedirect("matriculas");
                break;
            default: // list
                List<Matricula> lista = dao.findAll();
                request.setAttribute("matriculas", lista);
                request.getRequestDispatcher("matriculas.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String idStr = request.getParameter("id");
        int idAlumno = Integer.parseInt(request.getParameter("idAlumno"));
        int idPeriodo = Integer.parseInt(request.getParameter("idPeriodo"));
        java.sql.Date fechaMatricula = java.sql.Date.valueOf(request.getParameter("fechaMatricula"));
        String estado = request.getParameter("estado");

        if (idStr == null || idStr.isEmpty()) {
            // Nueva matrícula
            Matricula m = new Matricula();
            m.setIdAlumno(idAlumno);
            m.setIdPeriodo(idPeriodo);
            m.setFechaMatricula(fechaMatricula);
            m.setEstado(estado);
            dao.insert(m);
        } else {
            // Actualizar matrícula
            Matricula m = new Matricula();
            m.setIdMatricula(Integer.parseInt(idStr));
            m.setIdAlumno(idAlumno);
            m.setIdPeriodo(idPeriodo);
            m.setFechaMatricula(fechaMatricula);
            m.setEstado(estado);
            dao.update(m);
        }
        response.sendRedirect("matriculas");
    }
}
