<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Mi Tienda</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light d-flex flex-column min-vh-100">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/">
            Mi Tiendita
        </a>
    </div>
</nav>

<!-- CONTENIDO CENTRAL -->
<div class="container text-center my-auto py-5">
    <div class="card shadow-sm p-5 mx-auto" style="max-width: 500px;">
        <h1 class="text-primary fw-bold mb-4">Bienvenido a <span class="text-dark">Mi Tiendita</span></h1>
        <p class="text-secondary mb-4">Selecciona una de las secciones para comenzar:</p>

        <div class="d-grid gap-3">
            <a href="${pageContext.request.contextPath}/categories" class="btn btn-outline-primary btn-lg">
                <i class="bi bi-tags"></i> Categorías
            </a>
            <a href="${pageContext.request.contextPath}/products" class="btn btn-outline-success btn-lg">
                <i class="bi bi-box-seam"></i> Productos
            </a>
        </div>
    </div>
</div>

<!-- FOOTER -->
<footer class="mt-auto bg-primary text-white text-center py-3">
    <small>© 2025 Mi Tienda | Desarrollado por Naudy Domi Noa Laura</small>
</footer>

<!-- Bootstrap JS + Icons -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
