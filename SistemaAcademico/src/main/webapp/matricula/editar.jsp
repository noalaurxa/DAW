<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.Matricula" %>
<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.Alumno" %>
<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.PeriodoAcademico" %>
<%@ page import="java.util.List" %>
<%
    // Recuperamos las listas enviadas desde el servlet
    List<Alumno> alumnos = (List<Alumno>) request.getAttribute("listaAlumnos");
    List<PeriodoAcademico> periodos = (List<PeriodoAcademico>) request.getAttribute("listaPeriodos");
    Matricula matricula = (Matricula) request.getAttribute("matricula");
    boolean edit = matricula != null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><%= edit ? "Editar Matrícula" : "Agregar Matrícula" %></title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1><%= edit ? "Editar Matrícula" : "Agregar Matrícula" %></h1>

<form action="MatriculaController" method="post">
    <% if(edit) { %>
    <input type="hidden" name="idMatricula" value="<%= matricula.getIdMatricula() %>"/>
    <% } %>

    <!-- Selección de Alumno -->
    <label>Alumno:</label>
    <select name="idAlumno" required>
        <% if(alumnos != null) {
            for(Alumno a : alumnos) { %>
        <option value="<%= a.getIdAlumno() %>"
                <%= edit && matricula.getIdAlumno() == a.getIdAlumno() ? "selected" : "" %>>
            <%= a.getNombre() %>
        </option>
        <%   }
        } %>
    </select><br/>

    <!-- Selección de Periodo -->
    <label>Periodo:</label>
    <select name="idPeriodo" required>
        <% if(periodos != null) {
            for(PeriodoAcademico p : periodos) { %>
        <option value="<%= p.getIdPeriodo() %>"
                <%= edit && matricula.getIdPeriodo() == p.getIdPeriodo() ? "selected" : "" %>>
            <%= p.getNombrePeriodo() %>
        </option>
        <%   }
        } %>
    </select><br/>

    <!-- Fecha de Matrícula -->
    <label>Fecha Matrícula:</label>
    <input type="date" name="fechaMatricula"
           value="<%= edit && matricula.getFechaMatricula() != null ? matricula.getFechaMatricula() : "" %>" required/><br/>

    <!-- Estado -->
    <label>Estado:</label>
    <input type="text" name="estado"
           value="<%= edit && matricula.getEstado() != null ? matricula.getEstado() : "activo" %>" required/><br/>

    <input type="submit" value="<%= edit ? "Actualizar" : "Agregar" %>"/>
</form>

<a href="MatriculaController">Volver al Listado de Matrículas</a>
</body>
</html>
