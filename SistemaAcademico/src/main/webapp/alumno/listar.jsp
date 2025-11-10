<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.Alumno" %>
<%@ page import="java.util.List" %>
<%
    List<Alumno> listaAlumnos = (List<Alumno>) request.getAttribute("listaAlumnos");
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Lista de Alumnos</title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1>Lista de Alumnos</h1>
<a class="btn-nuevo" href="AlumnoController?action=nuevo">Agregar Alumno</a>
<table>
    <tr>
        <th>ID</th><th>Nombre</th><th>Apellido</th><th>DNI</th><th>Correo</th><th>Telefono</th><th>Fecha Nac.</th><th>Acciones</th>
    </tr>
    <% for(Alumno alumno : listaAlumnos) { %>
    <tr>
        <td><%= alumno.getIdAlumno() %></td>
        <td><%= alumno.getNombre() %></td>
        <td><%= alumno.getApellido() %></td>
        <td><%= alumno.getDni() %></td>
        <td><%= alumno.getCorreo() %></td>
        <td><%= alumno.getTelefono() %></td>
        <td><%= alumno.getFechaNacimiento() %></td>
        <td>
            <a class="editar" href="AlumnoController?action=editar&id=<%= alumno.getIdAlumno() %>">Editar</a>
            <a class="eliminar" href="AlumnoController?action=eliminar&id=<%= alumno.getIdAlumno() %>"
               onclick="return confirm('Seguro que deseas eliminar este alumno?');">Eliminar</a>
        </td>
    </tr>
    <% } %>
</table>
<a href="index.jsp">Volver al Menu Principal</a>
</body>
</html>
