<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.Alumno" %>
<%
    Alumno alumno = (Alumno) request.getAttribute("alumno");
    boolean edit = alumno != null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><%= edit ? "Editar Alumno" : "Agregar Alumno" %></title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1><%= edit ? "Editar Alumno" : "Agregar Alumno" %></h1>

<div class="form-container">
    <form action="AlumnoController" method="post">
        <% if(edit) { %>
        <input type="hidden" name="idAlumno" value="<%= alumno.getIdAlumno() %>"/>
        <% } %>

        Nombre:<br/>
        <input type="text" name="nombre" value="<%= edit ? alumno.getNombre() : "" %>" required/><br/>

        Apellido:<br/>
        <input type="text" name="apellido" value="<%= edit ? alumno.getApellido() : "" %>" required/><br/>

        DNI:<br/>
        <input type="text" name="dni" value="<%= edit ? alumno.getDni() : "" %>" required/><br/>

        Correo:<br/>
        <input type="text" name="correo" value="<%= edit ? alumno.getCorreo() : "" %>" required/><br/>

        Telefono:<br/>
        <input type="text" name="telefono" value="<%= edit ? alumno.getTelefono() : "" %>" required/><br/>

        Fecha Nac.:<br/>
        <input type="date" name="fechaNacimiento" value="<%= edit ? alumno.getFechaNacimiento() : "" %>" required/><br/>

        <input type="submit" value="<%= edit ? "Actualizar" : "Agregar" %>"/>
    </form>
    <a href="AlumnoController">Volver</a>
</div>
</body>
</html>
