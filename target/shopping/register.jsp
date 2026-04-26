<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register - Ellison Electronics</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="components/header.jsp" />

    <div class="container mt-5 mb-5">
        <div class="row justify-content-center">
            <div class="col-md-7">
                <div class="card glassmorphism p-4">
                    <h3 class="text-center mb-4 text-green">Create New Account</h3>
                    
                    <% if("error".equals(request.getParameter("msg"))) { %>
                        <div class="alert alert-danger text-center">Registration failed. Email might already exist!</div>
                    <% } %>

                    <form action="AuthServlet" method="POST">
                        <input type="hidden" name="action" value="register">
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Full Name</label>
                                <input type="text" class="form-control" name="name" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Email address</label>
                                <input type="email" class="form-control" name="email" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Mobile Number</label>
                                <input type="text" class="form-control" name="mobile" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Pin Code</label>
                                <input type="text" class="form-control" name="pincode" required>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Delivery Address</label>
                            <textarea class="form-control" name="address" rows="2" required></textarea>
                        </div>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Password</label>
                                <input type="password" class="form-control" name="password" id="pwd" required>
                            </div>
                            <div class="col-md-6 mb-4">
                                <label class="form-label">Confirm Password</label>
                                <input type="password" class="form-control" name="confirm_password" id="cpwd" required onkeyup="checkPwd()">
                                <small id="pwdMsg" class="text-danger"></small>
                            </div>
                        </div>
                        
                        <div class="d-flex justify-content-between">
                            <button type="reset" class="btn btn-secondary px-4">Reset</button>
                            <button type="submit" class="btn btn-green px-5" id="regBtn">Register</button>
                        </div>
                    </form>
                    <div class="text-center mt-3">
                        <p>Already have an account? <a href="login.jsp" class="text-green text-decoration-none">Login here</a></p>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="components/footer.jsp" />
    
    <script>
        function checkPwd() {
            var p = document.getElementById("pwd").value;
            var cp = document.getElementById("cpwd").value;
            var msg = document.getElementById("pwdMsg");
            var btn = document.getElementById("regBtn");
            if(p !== cp) {
                msg.innerHTML = "Passwords do not match!";
                btn.disabled = true;
            } else {
                msg.innerHTML = "";
                btn.disabled = false;
            }
        }
    </script>
</body>
</html>
