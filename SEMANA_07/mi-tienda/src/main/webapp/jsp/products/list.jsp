<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Listado de Productos</title>
    <!-- Bootstrap 5 -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>

<body class="bg-light">

<!-- NAVBAR -->
<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="${pageContext.request.contextPath}/products">Gestión de Productos</a>
        <div>
            <a href="${pageContext.request.contextPath}/categories" class="btn btn-outline-light btn-sm">
                Ir a Categorías
            </a>
        </div>
    </div>
</nav>

<!-- CONTENIDO -->
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2 class="text-primary fw-bold">Listado de Productos</h2>
        <a href="${pageContext.request.contextPath}/products?action=new" class="btn btn-success">
            <i class="bi bi-plus-circle"></i> Nuevo Producto
        </a>
    </div>

    <div class="card shadow-sm">
        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-striped align-middle text-center">
                    <thead class="table-primary">
                    <tr>
                        <th>ID</th>
                        <th>Nombre</th>
                        <th>Precio</th>
                        <th>Categoría</th>
                        <th>Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="p" items="${products}">
                        <tr>
                            <td>${p.id}</td>
                            <td>${p.name}</td>
                            <td><fmt:formatNumber value="${p.price}" type="currency" currencySymbol="S/ "/></td>
                            <td>${p.categoryId}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/products?action=edit&id=${p.id}"
                                   class="btn btn-sm btn-warning me-1">
                                    <i class="bi bi-pencil-square"></i> Editar
                                </a>
                                <a href="${pageContext.request.contextPath}/products?action=delete&id=${p.id}"
                                   class="btn btn-sm btn-danger"
                                   onclick="return confirm('¿Seguro que deseas eliminar este producto?');">
                                    <i class="bi bi-trash3"></i> Eliminar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <c:if test="${empty products}">
                <div class="alert alert-info text-center mt-3 mb-0">
                    No hay productos registrados.
                </div>
            </c:if>
        </div>
    </div>
</div>

<!-- Bootstrap JS + Icons -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
