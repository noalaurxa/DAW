<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.DetalleMatricula" %>
<%@ page import="java.util.List" %>
<%
    List<DetalleMatricula> listaDetalle = (List<DetalleMatricula>) request.getAttribute("listaDetalles");
    int idMatricula = (Integer) request.getAttribute("idMatricula");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Detalle de Matrícula</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1>Detalle de Matrícula: <%= idMatricula %></h1>

<a href="DetalleMatriculaController?action=nuevo&idMatricula=<%= idMatricula %>">Agregar Detalle</a>

<table>
    <tr>
        <th>ID</th>
        <th>ID Curso</th>
        <th>Estado</th>
        <th>Acciones</th>
    </tr>
    <% for(DetalleMatricula d : listaDetalle) { %>
    <tr>
        <td><%= d.getIdDetalle() %></td>
        <td><%= d.getIdCurso() %></td>
        <td><%= d.getEstado() %></td>
        <td>
            <a href="DetalleMatriculaController?action=editar&id=<%= d.getIdDetalle() %>&idMatricula=<%= idMatricula %>">Editar</a>
            <a href="DetalleMatriculaController?action=eliminar&id=<%= d.getIdDetalle() %>&idMatricula=<%= idMatricula %>"
               onclick="return confirm('Seguro que deseas eliminar este detalle?');">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>

<a href="MatriculaController">Volver a Matrículas</a>
</body>
</html>
