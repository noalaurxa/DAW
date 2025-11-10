package pe.edu.tecsup.lab01.sistemaacademico.controllers;

import pe.edu.tecsup.lab01.sistemaacademico.dao.PeriodoAcademicoDAO;
import pe.edu.tecsup.lab01.sistemaacademico.dao.Impl.PeriodoAcademicoDAOImpl;
import pe.edu.tecsup.lab01.sistemaacademico.model.PeriodoAcademico;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/PeriodoController")
public class PeriodoController extends HttpServlet {

    private final PeriodoAcademicoDAO periodoDAO = new PeriodoAcademicoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) action = "listar";

        switch(action) {
            case "nuevo":
                request.getRequestDispatcher("/periodoAcademico/agregar.jsp").forward(request, response);
                break;
            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                PeriodoAcademico pEditar = periodoDAO.obtenerPeriodo(idEditar);
                request.setAttribute("periodo", pEditar);
                request.getRequestDispatcher("/periodoAcademico/agregar.jsp").forward(request, response);
                break;
            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                periodoDAO.eliminarPeriodo(idEliminar);
                response.sendRedirect("PeriodoController");
                break;
            default:
                List<PeriodoAcademico> lista = periodoDAO.listarPeriodos();
                request.setAttribute("listaPeriodos", lista);
                request.getRequestDispatcher("/periodoAcademico/listar.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String idStr = request.getParameter("idPeriodo");
            PeriodoAcademico p = new PeriodoAcademico();
            p.setNombrePeriodo(request.getParameter("nombrePeriodo"));
            p.setFechaInicio(java.sql.Date.valueOf(request.getParameter("fechaInicio")));
            p.setFechaFin(java.sql.Date.valueOf(request.getParameter("fechaFin")));
            p.setEstado(request.getParameter("estado"));

            if(idStr == null || idStr.isEmpty()) {
                periodoDAO.agregarPeriodo(p);
            } else {
                p.setIdPeriodo(Integer.parseInt(idStr));
                periodoDAO.actualizarPeriodo(p);
            }

            response.sendRedirect("PeriodoController");
        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("PeriodoController?error=1");
        }
    }
}
