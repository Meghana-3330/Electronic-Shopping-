<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>My Orders - Ellison Electronics</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="components/header.jsp" />

    <div class="container mt-5 mb-5">
        <h2 class="mb-4 text-green">My Order History</h2>
        
        <% if("success".equals(request.getParameter("msg"))) { %>
            <div class="alert alert-success alert-dismissible fade show">
                <strong>Success!</strong> Your order has been placed successfully. You will receive an email confirmation shortly.
                <button type="button" class="btn-close" data-bs-dismiss="alert"></button>
            </div>
        <% } %>

        <div class="card shadow-sm">
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-striped align-middle mb-0">
                        <thead class="table-success">
                            <tr>
                                <th>Order ID</th>
                                <th>Product</th>
                                <th>Quantity</th>
                                <th>Amount Paid</th>
                                <th>Payment Method</th>
                                <th>Status</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="o" items="${orders}">
                                <tr>
                                    <td><span class="badge bg-secondary">${o.orderId}</span></td>
                                    <td>
                                        <div class="d-flex align-items-center">
                                            <img src="images/${o.image}" class="img-thumbnail me-2" style="height:50px;" onerror="this.src='https://via.placeholder.com/50?text=No+Image'">
                                            <span>${o.pname}</span>
                                        </div>
                                    </td>
                                    <td>${o.quantity}</td>
                                    <td class="fw-bold">₹${o.amount}</td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${o.paymentMethod == 'COD'}">
                                                <span class="badge bg-info text-dark">Cash on Delivery</span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-secondary">Card</span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                    <td>
                                        <c:choose>
                                            <c:when test="${o.shipped}">
                                                <span class="badge bg-success">Shipped <i class="bi bi-truck"></i></span>
                                            </c:when>
                                            <c:otherwise>
                                                <span class="badge bg-warning text-dark">Processing <i class="bi bi-hourglass-split"></i></span>
                                            </c:otherwise>
                                        </c:choose>
                                    </td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty orders}">
                                <tr>
                                    <td colspan="6" class="text-center py-4 text-muted">You have not placed any orders yet.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="components/footer.jsp" />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
