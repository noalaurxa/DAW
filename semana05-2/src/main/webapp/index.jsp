<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sistema de Matrícula - Login</title>
</head>
<body>
<h2>Iniciar Sesión</h2>
<form action="login" method="post">
    <label for="usuario">Usuario:</label>
    <input type="text" id="usuario" name="usuario" required><br><br>
    <label for="password">Contraseña:</label>
    <input type="password" id="password" name="password" required><br><br>
    <button type="submit">Ingresar</button>
</form>

<% if (request.getParameter("error") != null) { %>
<p style="color:red;">Usuario o contraseña incorrectos</p>
<% } %>
</body>
</html>
