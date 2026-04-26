<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Ellison Electronics - Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <!-- Bootstrap Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <!-- Custom CSS -->
    <link href="css/style.css" rel="stylesheet">
</head>
<body>
    <jsp:include page="components/header.jsp" />

    <div class="container mt-4">
        <h2 class="mb-4">Our Products</h2>
        <div class="row row-cols-1 row-cols-md-3 row-cols-lg-4 g-4">
            <c:forEach var="p" items="${products}">
                <div class="col">
                    <div class="card h-100 shadow-sm">
                        <!-- Normally you'd have an images folder, using placeholder if image not found -->
                        <img src="images/${p.image}" class="card-img-top product-img" alt="${p.pname}" onerror="this.src='https://via.placeholder.com/300x200?text=No+Image'">
                        <div class="card-body d-flex flex-column">
                            <h5 class="card-title text-truncate" title="${p.pname}">${p.pname}</h5>
                            <p class="card-text text-muted mb-1">${p.type}</p>
                            <h4 class="card-text text-green mb-3">₹${p.price}</h4>
                            <div class="mt-auto">
                                <form action="CartServlet" method="POST" class="d-inline">
                                    <input type="hidden" name="action" value="add">
                                    <input type="hidden" name="prodid" value="${p.pid}">
                                    <input type="hidden" name="quantity" value="1">
                                    <button type="submit" class="btn btn-outline-success w-100 mb-2">
                                        <i class="bi bi-cart-plus"></i> Add to Cart
                                    </button>
                                </form>
                                <a href="#" class="btn btn-green w-100">Buy Now</a>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
            <c:if test="${empty products}">
                <div class="col-12 text-center py-5">
                    <h4 class="text-muted">No products found.</h4>
                </div>
            </c:if>
        </div>
    </div>

    <jsp:include page="components/footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
