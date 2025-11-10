package pe.edu.tecsup.lab01.sistemaacademico.controllers;

import pe.edu.tecsup.lab01.sistemaacademico.dao.DetalleMatriculaDAO;
import pe.edu.tecsup.lab01.sistemaacademico.dao.Impl.DetalleMatriculaDAOImpl;
import pe.edu.tecsup.lab01.sistemaacademico.model.DetalleMatricula;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/DetalleMatriculaController")
public class DetalleMatriculaController extends HttpServlet {

    private final DetalleMatriculaDAO detalleDAO = new DetalleMatriculaDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int idMatricula = Integer.parseInt(request.getParameter("idMatricula"));

        if("nuevo".equals(action)) {
            request.setAttribute("idMatricula", idMatricula);
            request.getRequestDispatcher("/detalleMatricula/agregar.jsp").forward(request, response);
        } else if("eliminar".equals(action)) {
            int idDetalle = Integer.parseInt(request.getParameter("id"));
            detalleDAO.eliminarDetalle(idDetalle);
            response.sendRedirect("MatriculaController?action=verDetalle&id=" + idMatricula);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int idMatricula = Integer.parseInt(request.getParameter("idMatricula"));
        int idCurso = Integer.parseInt(request.getParameter("idCurso"));
        String estado = request.getParameter("estado");

        DetalleMatricula detalle = new DetalleMatricula();
        detalle.setIdMatricula(idMatricula);
        detalle.setIdCurso(idCurso);
        detalle.setEstado(estado);

        detalleDAO.agregarDetalle(detalle);

        response.sendRedirect("MatriculaController?action=verDetalle&id=" + idMatricula);
    }
}
