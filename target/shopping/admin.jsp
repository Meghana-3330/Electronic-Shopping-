<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard - Ellison Electronics</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="components/header.jsp" />

    <div class="container-fluid mt-4 mb-5">
        <div class="row">
            <!-- Sidebar -->
            <div class="col-md-2 mb-4">
                <div class="list-group">
                    <a href="AdminServlet?action=dashboard" class="list-group-item list-group-item-action active bg-green border-green">Manage Products</a>
                    <a href="AdminServlet?action=orders" class="list-group-item list-group-item-action">View All Orders</a>
                </div>
            </div>

            <!-- Main Content -->
            <div class="col-md-10">
                <div class="card shadow-sm">
                    <div class="card-header bg-white d-flex justify-content-between align-items-center">
                        <h4 class="mb-0 text-green">Product Inventory</h4>
                        <button class="btn btn-sm btn-green" data-bs-toggle="modal" data-bs-target="#addProductModal">Add New Product</button>
                    </div>
                    <div class="card-body p-0">
                        <div class="table-responsive">
                            <table class="table table-hover align-middle mb-0">
                                <thead class="table-light">
                                    <tr>
                                        <th>ID</th>
                                        <th>Image</th>
                                        <th>Name</th>
                                        <th>Category</th>
                                        <th>Price</th>
                                        <th>Qty</th>
                                        <th>Actions</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="p" items="${products}">
                                        <tr>
                                            <td>${p.pid}</td>
                                            <td><img src="images/${p.image}" height="40" onerror="this.src='https://via.placeholder.com/40'"></td>
                                            <td>${p.pname}</td>
                                            <td>${p.type}</td>
                                            <td>₹${p.price}</td>
                                            <td>${p.quantity}</td>
                                            <td>
                                                <a href="AdminServlet?action=deleteProduct&pid=${p.pid}" class="btn btn-sm btn-danger" onclick="return confirm('Delete this product?')">Delete</a>
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

    <!-- Add Product Modal -->
    <div class="modal fade" id="addProductModal" tabindex="-1">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="AdminServlet" method="POST">
                    <input type="hidden" name="action" value="addProduct">
                    <div class="modal-header text-white bg-green">
                        <h5 class="modal-title">Add New Product</h5>
                        <button type="button" class="btn-close btn-close-white" data-bs-dismiss="modal"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label>Product Name</label>
                            <input type="text" name="pname" class="form-control" required>
                        </div>
                        <div class="mb-3">
                            <label>Category</label>
                            <select name="type" class="form-control" required>
                                <option value="Mobiles">Mobiles</option>
                                <option value="TV">TV</option>
                                <option value="Laptops">Laptops</option>
                                <option value="Camera">Camera</option>
                                <option value="Speakers">Speakers</option>
                                <option value="Tablets">Tablets</option>
                            </select>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label>Price (₹)</label>
                                <input type="number" name="price" step="0.01" class="form-control" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label>Quantity</label>
                                <input type="number" name="quantity" class="form-control" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label>Image Filename (e.g. item.jpg)</label>
                            <input type="text" name="image" class="form-control" required>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-green">Save Product</button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
