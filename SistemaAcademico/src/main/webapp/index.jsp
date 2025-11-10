<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Sistema Academico</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
<h1>Bienvenido al Sistema Academico</h1>

<div class="dashboard">
    <div><a class="btn-alumnos" href="${pageContext.request.contextPath}/AlumnoController">Gestionar Alumnos</a></div>
    <div><a class="btn-periodos" href="${pageContext.request.contextPath}/PeriodoController">Gestionar Periodos Academicos</a></div>
    <div><a class="btn-matriculas" href="${pageContext.request.contextPath}/MatriculaController">Gestionar Matriculas</a></div>
</div>

</body>
</html>
