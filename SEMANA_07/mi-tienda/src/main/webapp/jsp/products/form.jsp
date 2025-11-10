<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Producto</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/products">Gestión de Productos</a>
        <div>
            <a href="${pageContext.request.contextPath}/categories" class="btn btn-outline-light btn-sm">Ir a Categorías</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="card shadow-sm mx-auto" style="max-width: 650px;">
        <div class="card-header bg-white text-center">
            <h4 class="text-primary mb-0">
                <c:choose>
                    <c:when test="${not empty product}">Editar Producto</c:when>
                    <c:otherwise>Nuevo Producto</c:otherwise>
                </c:choose>
            </h4>
        </div>

        <div class="card-body">
            <form method="post" action="${pageContext.request.contextPath}/products">
                <input type="hidden" name="id" value="${product.id}" />

                <div class="mb-3">
                    <label class="form-label fw-semibold">Nombre del producto</label>
                    <input type="text" name="name" value="${product.name}" class="form-control" required>
                </div>

                <div class="mb-3">
                    <label class="form-label fw-semibold">Precio (S/)</label>
                    <input type="number" step="0.01" name="price" value="${product.price}" class="form-control" required>
                </div>

                <div class="mb-4">
                    <label class="form-label fw-semibold">Categoría</label>
                    <select name="categoryId" class="form-select">
                        <option value="">-- Seleccionar categoría --</option>
                        <c:forEach var="cat" items="${categories}">
                            <option value="${cat.id}" <c:if test="${product != null && product.categoryId == cat.id}">selected</c:if>>
                                    ${cat.name}
                            </option>
                        </c:forEach>
                    </select>
                </div>

                <div class="d-flex justify-content-between">
                    <a href="${pageContext.request.contextPath}/products" class="btn btn-outline-secondary">
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
