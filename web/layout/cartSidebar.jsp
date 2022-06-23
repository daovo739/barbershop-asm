<%-- 
    Document   : cartSidebar
    Created on : Jun 21, 2022, 2:46:06 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<div class="offcanvas offcanvas-end bg-dark text-white vh-100" data-bs-scroll="true" tabindex="-1" id="offcanvasWithBothOptions" aria-labelledby="offcanvasWithBothOptionsLabel">
    <div class="offcanvas-header">
        <h5 class="offcanvas-title" id="offcanvasWithBothOptionsLabel">Your Cart</h5>
        <button type="button" class="btn-close text-reset btn-close-white" data-bs-dismiss="offcanvas" aria-label="Close"></button>
    </div>
    <div class="offcanvas-body h-25" >
        <div class="row cart-list">

        </div>
    </div>
    <div class="bottom d-flex flex-column" style="padding: 0 16px;margin-bottom: 16px">
        <div class="d-flex justify-content-between">
            <p>Total</p>
            <p>$${cart.getTotalCostCart()}</p>
        </div>
        <button class="btn btn-primary"><a href="url" class="text-white text-decoration-none">Checkout</a></button>
    </div>
</div>