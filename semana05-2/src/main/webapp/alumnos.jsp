<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="pe.edu.tecsup.lab01.semana06.model.entities.Alumno" %>

<%
    // Recibimos parámetros desde el controller
    String action = request.getParameter("action");
    if (action == null) action = "list";

    Alumno alumno = (Alumno) request.getAttribute("alumno");
    List<Alumno> alumnos = (List<Alumno>) request.getAttribute("alumnos");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestión de Alumnos</title>
</head>
<body>
<h2>Gestión de Alumnos</h2>

<!-- Formulario de agregar/editar -->
<% if ("edit".equals(action) || "add".equals(action)) { %>
<form method="post" action="alumnos">
    <input type="hidden" name="id" value="<%= (alumno != null ? alumno.getIdAlumno() : "") %>">
    <label>Nombre:</label>
    <input type="text" name="nombre" value="<%= (alumno != null ? alumno.getNombre() : "") %>" required>
    <label>Apellido:</label>
    <input type="text" name="apellido" value="<%= (alumno != null ? alumno.getApellido() : "") %>" required>
    <input type="submit" value="Guardar">
</form>
<a href="alumnos">Volver al listado</a>

<% } else { %>
<a href="alumnos?action=add">Agregar Alumno</a>
<table border="1" cellpadding="5" cellspacing="0">
    <tr>
        <th>ID</th>
        <th>Nombre</th>
        <th>Apellido</th>
        <th>Acciones</th>
    </tr>
    <% if (alumnos != null) {
        for (Alumno a : alumnos) { %>
    <tr>
        <td><%= a.getIdAlumno() %></td>
        <td><%= a.getNombre() %></td>
        <td><%= a.getApellido() %></td>
        <td>
            <a href="alumnos?action=edit&id=<%= a.getIdAlumno() %>">Editar</a> |
            <a href="alumnos?action=delete&id=<%= a.getIdAlumno() %>"
               onclick="return confirm('¿Seguro que desea eliminar?')">Eliminar</a>
        </td>
    </tr>
    <%   }
    } %>
</table>
<% } %>

</body>
</html>
