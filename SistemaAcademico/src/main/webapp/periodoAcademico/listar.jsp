<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.PeriodoAcademico" %>
<%@ page import="java.util.List" %>
<%
    List<PeriodoAcademico> listaPeriodos = (List<PeriodoAcademico>) request.getAttribute("listaPeriodos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Periodos Academicos</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1>Lista de Periodos Academicos</h1>
<a class="btn-nuevo" href="PeriodoController?action=nuevo">Agregar Periodo</a>
<table>
    <tr>
        <th>ID</th><th>Nombre</th><th>Inicio</th><th>Fin</th><th>Estado</th><th>Acciones</th>
    </tr>
    <% for(PeriodoAcademico p : listaPeriodos) { %>
    <tr>
        <td><%= p.getIdPeriodo() %></td>
        <td><%= p.getNombrePeriodo() %></td>
        <td><%= p.getFechaInicio() %></td>
        <td><%= p.getFechaFin() %></td>
        <td><%= p.getEstado() %></td>
        <td>
            <a class="editar" href="PeriodoController?action=editar&id=<%= p.getIdPeriodo() %>">Editar</a>
            <a class="eliminar" href="PeriodoController?action=eliminar&id=<%= p.getIdPeriodo() %>"
               onclick="return confirm('Seguro que deseas eliminar este periodo?');">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>
<a href="index.jsp">Volver al Menu Principal</a>
</body>
</html>
