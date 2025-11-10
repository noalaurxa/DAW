<%@ page import="pe.edu.tecsup.lab01.sistemaacademico.model.PeriodoAcademico" %>
<%
    PeriodoAcademico periodo = (PeriodoAcademico) request.getAttribute("periodo");
    boolean edit = periodo != null;
%>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title><%= edit ? "Editar Periodo" : "Agregar Periodo" %></title>
    <link rel="stylesheet" href="css/estilos.css">
</head>
<body>
<h1><%= edit ? "Editar Periodo Academico" : "Agregar Periodo Academico" %></h1>
<form action="PeriodoController" method="post">
    <% if(edit) { %>
    <input type="hidden" name="idPeriodo" value="<%= periodo.getIdPeriodo() %>"/>
    <% } %>
    Nombre: <input type="text" name="nombrePeriodo" value="<%= edit ? periodo.getNombrePeriodo() : "" %>"/><br/>
    Fecha Inicio: <input type="date" name="fechaInicio" value="<%= edit ? periodo.getFechaInicio() : "" %>"/><br/>
    Fecha Fin: <input type="date" name="fechaFin" value="<%= edit ? periodo.getFechaFin() : "" %>"/><br/>
    Estado: <input type="text" name="estado" value="<%= edit ? periodo.getEstado() : "" %>"/><br/>
    <input type="submit" value="<%= edit ? "Actualizar" : "Agregar" %>"/>
</form>
<a href="PeriodoController">Volver</a>
</body>
</html>
