<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Categoría</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/categories">Gestión de Categorías</a>
        <div>
            <a href="${pageContext.request.contextPath}/products" class="btn btn-outline-light btn-sm">Ir a Productos</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="card shadow-sm mx-auto" style="max-width: 600px;">
        <div class="card-header bg-white text-center">
            <h4 class="text-primary mb-0">
                <c:choose>
                    <c:when test="${not empty category}">Editar Categoría</c:when>
                    <c:otherwise>Nueva Categoría</c:otherwise>
                </c:choose>
            </h4>
        </div>

        <div class="card-body">
            <form method="post" action="${pageContext.request.contextPath}/categories">
                <input type="hidden" name="id" value="${category.id}" />

                <div class="mb-3">
                    <label class="form-label fw-semibold">Nombre</label>
                    <input type="text" name="name" value="${category.name}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Descripción</label>
                    <input type="text" name="description" value="${category.description}" class="form-control">
                </div>

                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/categories" class="btn btn-outline-secondary">
                        <i class="bi bi-arrow-left-circle"></i> Volver
                    </a>
                    <button type="submit" class="btn btn-success">
                        <i class="bi bi-save"></i> Guardar
                    </button>
                </div>
            </form>
        </div>
    </div>
</div>

<!-- Bootstrap JS y Icons -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
