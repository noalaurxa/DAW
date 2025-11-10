<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.edu.tecsup.lab01.semana06.model.entities.*" %>

<%
    String action = request.getParameter("action");
    if (action == null) action = "list";

    Matricula matricula = (Matricula) request.getAttribute("matricula");
    List<Matricula> matriculas = (List<Matricula>) request.getAttribute("matriculas");
    List<Alumno> alumnos = (List<Alumno>) request.getAttribute("alumnos");
    List<PeriodoAcademico> periodos = (List<PeriodoAcademico>) request.getAttribute("periodos");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Matrículas</title>
</head>
<body>
<h2>Gestión de Matrículas</h2>

<% if ("edit".equals(action) || "add".equals(action)) { %>
<form method="post" action="matriculas">
    <input type="hidden" name="id" value="<%= (matricula != null ? matricula.getIdMatricula() : "") %>">

    <label>Alumno:</label>
    <select name="idAlumno" required>
        <option value="">--Seleccione--</option>
        <% for (Alumno a : alumnos) { %>
        <option value="<%= a.getIdAlumno() %>"
                <%= (matricula != null && matricula.getIdAlumno() == a.getIdAlumno() ? "selected" : "") %>>
            <%= a.getNombre() + " " + a.getApellido() %>
        </option>
        <% } %>
    </select>

    <label>Periodo:</label>
    <select name="idPeriodo" required>
        <option value="">--Seleccione--</option>
        <% for (PeriodoAcademico p : periodos) { %>
        <option value="<%= p.getIdPeriodo() %>"
                <%= (matricula != null && matricula.getIdPeriodo() == p.getIdPeriodo() ? "selected" : "") %>>
            <%= p.getNombrePeriodo() %>
        </option>
        <% } %>
    </select>

    <label>Fecha de Matrícula:</label>
    <input type="date" name="fechaMatricula"
           value="<%= (matricula != null ? matricula.getFechaMatricula() : "") %>" required>

    <label>Estado:</label>
    <input type="text" name="estado" value="<%= (matricula != null ? matricula.getEstado() : "") %>" required>

    <input type="submit" value="Guardar">
</form>
<a href="matriculas">Volver al listado</a>

<% } else { %>
<a href="matriculas?action=add">Agregar Matrícula</a>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Alumno</th>
        <th>Periodo</th>
        <th>Fecha</th>
        <th>Estado</th>
        <th>Acciones</th>
    </tr>
    <% if (matriculas != null) {
        for (Matricula m : matriculas) { %>
    <tr>
        <td><%= m.getIdMatricula() %></td>
        <td><%= m.getIdAlumno() %></td>
        <td><%= m.getIdPeriodo() %></td>
        <td><%= m.getFechaMatricula() %></td>
        <td><%= m.getEstado() %></td>
        <td>
            <a href="matriculas?action=edit&id=<%= m.getIdMatricula() %>">Editar</a> |
            <a href="matriculas?action=delete&id=<%= m.getIdMatricula() %>"
               onclick="return confirm('¿Seguro que desea eliminar?')">Eliminar</a>
        </td>
    </tr>
    <%   }
    } %>
</table>
<% } %>

</body>
</html>
