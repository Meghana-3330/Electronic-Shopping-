<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-green shadow-sm">
    <div class="container-fluid">
        <a class="navbar-brand header-title" href="ProductServlet">
            <i class="bi bi-cpu"></i> Ellison Electronics
        </a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <li class="nav-item">
                    <a class="nav-link active" href="ProductServlet">Home</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown">
                        Category
                    </a>
                    <ul class="dropdown-menu">
                        <li><a class="dropdown-item" href="ProductServlet?category=Mobiles">Mobiles</a></li>
                        <li><a class="dropdown-item" href="ProductServlet?category=TV">TV</a></li>
                        <li><a class="dropdown-item" href="ProductServlet?category=Laptops">Laptops</a></li>
                        <li><a class="dropdown-item" href="ProductServlet?category=Camera">Camera</a></li>
                        <li><a class="dropdown-item" href="ProductServlet?category=Speakers">Speakers</a></li>
                        <li><a class="dropdown-item" href="ProductServlet?category=Tablets">Tablets</a></li>
                        <li><a class="dropdown-item" href="ProductServlet?category=Dresses">Dresses</a></li>
                    </ul>
                </li>
            </ul>
            <form class="d-flex me-3" action="ProductServlet" method="GET">
                <input class="form-control me-2" type="search" name="search" placeholder="Search products...">
                <button class="btn btn-light text-green fw-bold" type="submit">Search</button>
            </form>
            <ul class="navbar-nav">
                <c:if test="${not empty sessionScope.user}">
                    <c:if test="${sessionScope.user.email == 'admin@ellison.com'}">
                        <li class="nav-item"><a class="nav-link" href="AdminServlet?action=dashboard">Admin</a></li>
                    </c:if>
                    <c:if test="${sessionScope.user.email != 'admin@ellison.com'}">
                        <li class="nav-item"><a class="nav-link" href="CartServlet?action=view"><i class="bi bi-cart"></i> Cart</a></li>
                        <li class="nav-item"><a class="nav-link" href="OrderServlet?action=history">Orders</a></li>
                    </c:if>
                    <li class="nav-item"><a class="nav-link fw-bold" href="#">Hi, ${sessionScope.user.name}</a></li>
                    <li class="nav-item"><a class="nav-link btn btn-danger text-white px-3 ms-2" href="AuthServlet?action=logout">Logout</a></li>
                </c:if>
                <c:if test="${empty sessionScope.user}">
                    <li class="nav-item"><a class="nav-link" href="login.jsp">Login</a></li>
                    <li class="nav-item"><a class="nav-link btn btn-light text-green px-3 ms-2 fw-bold" href="register.jsp">Register</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</nav>

<div class="bg-dark text-white text-center py-2">
    <small>We specialize in Electronics</small>
</div>
