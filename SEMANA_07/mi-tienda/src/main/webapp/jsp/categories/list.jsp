<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Categorías</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-primary shadow-sm">
    <div class="container">
        <a class="navbar-brand fw-bold" href="#">Gestión de Categorías</a>
        <div>
            <a href="${pageContext.request.contextPath}/products" class="btn btn-outline-light btn-sm">Ir a Productos</a>
        </div>
    </div>
</nav>

<div class="container mt-5">
    <div class="card shadow-sm">
        <div class="card-header d-flex justify-content-between align-items-center bg-white">
            <h4 class="mb-0 text-primary">Listado de Categorías</h4>
            <a href="${pageContext.request.contextPath}/categories?action=new" class="btn btn-success">
                <i class="bi bi-plus-circle"></i> Nueva categoría
            </a>
        </div>

        <div class="card-body">
            <div class="table-responsive">
                <table class="table table-hover align-middle">
                    <thead class="table-primary">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Nombre</th>
                        <th scope="col">Descripción</th>
                        <th scope="col" class="text-center">Acciones</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="cat" items="${categories}">
                        <tr>
                            <td>${cat.id}</td>
                            <td class="fw-semibold">${cat.name}</td>
                            <td>${cat.description}</td>
                            <td class="text-center">
                                <a href="${pageContext.request.contextPath}/categories?action=edit&id=${cat.id}"
                                   class="btn btn-warning btn-sm">
                                    Editar
                                </a>
                                <a href="${pageContext.request.contextPath}/categories?action=delete&id=${cat.id}"
                                   class="btn btn-danger btn-sm"
                                   onclick="return confirm('¿Eliminar esta categoría?')">
                                    Eliminar
                                </a>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${empty categories}">
                        <tr>
                            <td colspan="4" class="text-center text-muted py-4">
                                No hay categorías registradas.
                            </td>
                        </tr>
                    </c:if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap Icons y JS -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>
