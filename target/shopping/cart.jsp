<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Shopping Cart - Ellison Electronics</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="components/header.jsp" />

    <div class="container mt-5 mb-5">
        <h2 class="mb-4 text-green"><i class="bi bi-cart3"></i> Your Shopping Cart</h2>
        
        <c:set var="total" value="0"/>
        
        <div class="card shadow-sm mb-4">
            <div class="card-body p-0">
                <div class="table-responsive">
                    <table class="table table-hover align-middle mb-0">
                        <thead class="table-light">
                            <tr>
                                <th>Product Image</th>
                                <th>Name</th>
                                <th>Price</th>
                                <th style="width: 150px;">Quantity</th>
                                <th>Amount</th>
                                <th>Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${cartItems}">
                                <tr>
                                    <td>
                                        <img src="images/${item.image}" class="cart-img img-thumbnail" alt="${item.pname}" onerror="this.src='https://via.placeholder.com/80?text=No+Image'">
                                    </td>
                                    <td class="fw-bold">${item.pname}</td>
                                    <td>₹${item.price}</td>
                                    <td>
                                        <form action="CartServlet" method="POST" class="d-flex align-items-center">
                                            <input type="hidden" name="action" value="update">
                                            <input type="hidden" name="prodid" value="${item.prodid}">
                                            <input type="number" name="quantity" value="${item.quantity}" min="1" max="10" class="form-control form-control-sm me-2" style="width: 60px;">
                                            <button type="submit" class="btn btn-sm btn-outline-primary"><i class="bi bi-arrow-repeat"></i></button>
                                        </form>
                                    </td>
                                    <td class="fw-bold text-success">₹${item.price * item.quantity}</td>
                                    <td>
                                        <a href="CartServlet?action=remove&prodid=${item.prodid}" class="btn btn-sm btn-danger"><i class="bi bi-trash"></i> Remove</a>
                                    </td>
                                </tr>
                                <c:set var="total" value="${total + (item.price * item.quantity)}"/>
                            </c:forEach>
                            <c:if test="${empty cartItems}">
                                <tr>
                                    <td colspan="6" class="text-center py-4 text-muted">Your cart is empty. <a href="ProductServlet" class="text-green text-decoration-none">Continue Shopping</a></td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <c:if test="${not empty cartItems}">
            <div class="row justify-content-end">
                <div class="col-md-4">
                    <div class="card shadow-sm">
                        <div class="card-body text-end">
                            <h4>Total Amount: <span class="text-green fw-bold">₹${total}</span></h4>
                            <div class="mt-3">
                                <a href="ProductServlet" class="btn btn-secondary me-2">Cancel</a>
                                <a href="OrderServlet?action=checkout_page" class="btn btn-green">Proceed to Checkout</a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:if>
    </div>

    <jsp:include page="components/footer.jsp" />
</body>
</html>
