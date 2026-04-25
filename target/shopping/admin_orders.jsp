<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin - Manage Orders</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="components/header.jsp" />

    <div class="container-fluid mt-4 mb-5">
        <div class="row">
            <div class="col-md-2 mb-4">
                <div class="list-group">
                    <a href="AdminServlet?action=dashboard" class="list-group-item list-group-item-action">Manage Products</a>
                    <a href="AdminServlet?action=orders" class="list-group-item list-group-item-action active bg-green border-green">View All Orders</a>
                </div>
            </div>

            <div class="col-md-10">
                <div class="card shadow-sm">
                    <div class="card-header bg-white">
                        <h4 class="mb-0 text-green">Customer Orders</h4>
                    </div>
                    <div class="card-body p-0">
                        <div class="table-responsive">
                            <table class="table table-striped align-middle mb-0">
                                <thead class="table-light">
                                    <tr>
                                        <th>Order ID</th>
                                        <th>Customer Email</th>
                                        <th>Product</th>
                                        <th>Qty</th>
                                        <th>Amount</th>
                                        <th>Status</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="o" items="${orders}">
                                        <tr>
                                            <td>${o.orderId}</td>
                                            <td>${o.username}</td>
                                            <td>${o.pname}</td>
                                            <td>${o.quantity}</td>
                                            <td>₹${o.amount}</td>
                                            <td>
                                                <c:choose>
                                                    <c:when test="${o.shipped}">
                                                        <span class="badge bg-success">Shipped</span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="badge bg-warning text-dark">Pending</span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>
                                            <td>
                                                <c:if test="${!o.shipped}">
                                                    <form action="AdminServlet" method="POST" class="m-0 p-0">
                                                        <input type="hidden" name="action" value="markShipped">
                                                        <input type="hidden" name="orderId" value="${o.orderId}">
                                                        <input type="hidden" name="prodId" value="${o.prodId}">
                                                        <input type="hidden" name="username" value="${o.username}">
                                                        <button type="submit" class="btn btn-sm btn-primary">Mark Shipped</button>
                                                    </form>
                                                </c:if>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
