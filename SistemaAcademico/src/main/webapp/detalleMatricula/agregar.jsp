<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.DetalleMatricula" %>
<%
    String idMatriculaParam = request.getParameter("idMatricula");
    if(idMatriculaParam == null) {
        out.println("Error: Matrícula no definida.");
        return;
    }
    int idMatricula = Integer.parseInt(idMatriculaParam);
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Agregar Curso a Matrícula</title>
    <link rel="stylesheet" href="../css/estilos.css">
</head>
<body>
<h1>Agregar Curso a Matrícula ID: <%= idMatricula %></h1>

<form action="../DetalleMatriculaController" method="post">
    <input type="hidden" name="idMatricula" value="<%= idMatricula %>"/>

    Curso (ID): <input type="number" name="idCurso" required/><br/>
    Estado: <input type="text" name="estado" value="matriculado" readonly/><br/><br/>

    <input type="submit" value="Agregar Curso"/>
</form>

<a href="../MatriculaController?action=verDetalle&idMatricula=<%= idMatricula %>">Volver al Detalle</a>
</body>
</html>
