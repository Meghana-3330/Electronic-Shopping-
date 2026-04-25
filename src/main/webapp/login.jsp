<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login - Ellison Electronics</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="components/header.jsp" />

    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-5">
                <div class="card glassmorphism p-4">
                    <h3 class="text-center mb-4 text-green">Login to Your Account</h3>
                    
                    <% String msg = request.getParameter("msg"); 
                       if("invalid".equals(msg)) { %>
                        <div class="alert alert-danger text-center">Invalid Email or Password!</div>
                    <% } else if("registered".equals(msg)) { %>
                        <div class="alert alert-success text-center">Registration successful! Please login.</div>
                    <% } else if("loggedout".equals(msg)) { %>
                        <div class="alert alert-info text-center">You have been logged out successfully.</div>
                    <% } %>

                    <form action="AuthServlet" method="POST">
                        <input type="hidden" name="action" value="login">
                        <div class="mb-3">
                            <label class="form-label">Email address</label>
                            <input type="email" class="form-control" name="email" required>
                        </div>
                        <div class="mb-4">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" name="password" required>
                        </div>
                        <button type="submit" class="btn btn-green w-100 py-2">Login</button>
                    </form>
                    <div class="text-center mt-3">
                        <p>Don't have an account? <a href="register.jsp" class="text-green text-decoration-none">Register here</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="components/footer.jsp" />
</body>
</html>
