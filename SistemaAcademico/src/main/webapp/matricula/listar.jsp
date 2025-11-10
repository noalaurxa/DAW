<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.Matricula" %>
<%@ page import="java.util.List" %>

<%
    List<Matricula> listaMatriculas = (List<Matricula>) request.getAttribute("listaMatriculas");
%>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Listado de Matrículas</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1>Listado de Matrículas</h1>

<a class="btn-nuevo" href="MatriculaController?action=nuevo">Nueva Matrícula</a>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Alumno</th>
        <th>Periodo</th>
        <th>Fecha Matrícula</th>
        <th>Estado</th>
        <th>Acciones</th>
    </tr>
    </thead>
    <tbody>
    <% if(listaMatriculas != null && !listaMatriculas.isEmpty()) {
        for(Matricula m : listaMatriculas) {
            String nombreAlumno = (m.getAlumno() != null && m.getAlumno().getNombre() != null)
                    ? m.getAlumno().getNombre() : "N/A";
            String nombrePeriodo = (m.getPeriodo() != null && m.getPeriodo().getNombrePeriodo() != null)
                    ? m.getPeriodo().getNombrePeriodo() : "N/A";
    %>
    <tr>
        <td><%= m.getIdMatricula() %></td>
        <td><%= nombreAlumno %></td>
        <td><%= nombrePeriodo %></td>
        <td><%= m.getFechaMatricula() != null ? m.getFechaMatricula() : "" %></td>
        <td><%= m.getEstado() != null ? m.getEstado() : "" %></td>
        <td>
            <a class="editar" href="MatriculaController?action=editar&id=<%= m.getIdMatricula() %>">Editar</a>
            <a class="eliminar" href="MatriculaController?action=eliminar&id=<%= m.getIdMatricula() %>"
               onclick="return confirm('¿Seguro que deseas eliminar esta matrícula?');">Eliminar</a>
        </td>
    </tr>
    <%  }
    } else { %>
    <tr>
        <td colspan="6" style="text-align:center;">No hay matrículas registradas</td>
    </tr>
    <% } %>
    </tbody>
</table>

<a href="index.jsp">Volver al Inicio</a>
</body>
</html>
