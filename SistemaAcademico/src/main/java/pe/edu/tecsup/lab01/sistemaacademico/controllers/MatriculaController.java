package pe.edu.tecsup.lab01.sistemaacademico.controllers;

import pe.edu.tecsup.lab01.sistemaacademico.dao.MatriculaDAO;
import pe.edu.tecsup.lab01.sistemaacademico.dao.Impl.MatriculaDAOImpl;
import pe.edu.tecsup.lab01.sistemaacademico.dao.AlumnoDAO;
import pe.edu.tecsup.lab01.sistemaacademico.dao.PeriodoAcademicoDAO;
import pe.edu.tecsup.lab01.sistemaacademico.dao.Impl.AlumnoDAOImpl;
import pe.edu.tecsup.lab01.sistemaacademico.dao.Impl.PeriodoAcademicoDAOImpl;
import pe.edu.tecsup.lab01.sistemaacademico.model.Matricula;
import pe.edu.tecsup.lab01.sistemaacademico.model.Alumno;
import pe.edu.tecsup.lab01.sistemaacademico.model.PeriodoAcademico;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/MatriculaController")
public class MatriculaController extends HttpServlet {

    private final MatriculaDAO matriculaDAO = new MatriculaDAOImpl();
    private final AlumnoDAO alumnoDAO = new AlumnoDAOImpl();
    private final PeriodoAcademicoDAO periodoDAO = new PeriodoAcademicoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if(action == null) action = "listar";

        switch(action) {
            case "nuevo":
                request.setAttribute("listaAlumnos", alumnoDAO.listarAlumnos());
                request.setAttribute("listaPeriodos", periodoDAO.listarPeriodos());
                request.getRequestDispatcher("/matricula/agregar.jsp").forward(request, response);
                break;

            case "editar":
                int idEditar = Integer.parseInt(request.getParameter("id"));
                Matricula m = matriculaDAO.obtenerMatricula(idEditar);
                request.setAttribute("matricula", m);
                request.setAttribute("listaAlumnos", alumnoDAO.listarAlumnos());
                request.setAttribute("listaPeriodos", periodoDAO.listarPeriodos());
                request.getRequestDispatcher("/matricula/agregar.jsp").forward(request, response);
                break;

            case "eliminar":
                int idEliminar = Integer.parseInt(request.getParameter("id"));
                matriculaDAO.eliminarMatricula(idEliminar);
                response.sendRedirect("MatriculaController");
                break;

            default:
                List<Matricula> lista = matriculaDAO.listarMatriculas();
                request.setAttribute("listaMatriculas", lista);
                request.getRequestDispatcher("/matricula/listar.jsp").forward(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Matricula m = new Matricula();
            String idStr = request.getParameter("idMatricula");

            if(idStr != null && !idStr.isEmpty()) {
                m.setIdMatricula(Integer.parseInt(idStr));
            }

            m.setIdAlumno(Integer.parseInt(request.getParameter("idAlumno")));
            m.setIdPeriodo(Integer.parseInt(request.getParameter("idPeriodo")));
            m.setFechaMatricula(java.sql.Date.valueOf(request.getParameter("fechaMatricula")));
            m.setEstado(request.getParameter("estado"));

            if(idStr == null || idStr.isEmpty()) {
                matriculaDAO.agregarMatricula(m);
            } else {
                matriculaDAO.actualizarMatricula(m);
            }

            response.sendRedirect("MatriculaController");

        } catch(Exception e) {
            e.printStackTrace();
            response.sendRedirect("MatriculaController?error=1");
        }
    }
}
