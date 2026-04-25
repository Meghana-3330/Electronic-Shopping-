<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Checkout - Ellison Electronics</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="css/style.css" rel="stylesheet">
</head>
<body class="bg-light">
    <jsp:include page="components/header.jsp" />

    <div class="container mt-5 mb-5">
        <h2 class="mb-4 text-green text-center">Checkout securely</h2>
        
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card shadow-sm p-4">
                    <h4 class="mb-3">Order Summary: ₹${total}</h4>
                    <hr>
                    <form action="OrderServlet" method="POST">
                        <input type="hidden" name="action" value="checkout">
                        
                        <h5 class="mt-3 mb-3">Shipping Details</h5>
                        <div class="mb-3">
                            <label class="form-label">Full Name</label>
                            <input type="text" class="form-control" value="${sessionScope.user.name}" readonly>
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Shipping Address</label>
                            <textarea class="form-control" name="address" rows="3" required>${sessionScope.user.address}, PIN: ${sessionScope.user.pincode}</textarea>
                            <div class="form-text">You can update the delivery address for this order here.</div>
                        </div>

                        <h5 class="mt-4 mb-3">Payment Details (Dummy Gateway)</h5>
                        <div class="row">
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Card Name</label>
                                <input type="text" class="form-control" placeholder="Name on Card" required>
                            </div>
                            <div class="col-md-6 mb-3">
                                <label class="form-label">Card Number</label>
                                <input type="text" class="form-control" placeholder="XXXX-XXXX-XXXX-XXXX" required>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-4 mb-3">
                                <label class="form-label">Expiry Month</label>
                                <input type="text" class="form-control" placeholder="MM" required>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label class="form-label">Expiry Year</label>
                                <input type="text" class="form-control" placeholder="YYYY" required>
                            </div>
                            <div class="col-md-4 mb-3">
                                <label class="form-label">CVV</label>
                                <input type="password" class="form-control" placeholder="***" required>
                            </div>
                        </div>

                        <div class="d-grid mt-4">
                            <button type="submit" class="btn btn-green btn-lg">Pay Now (₹${total})</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="components/footer.jsp" />
</body>
</html>
