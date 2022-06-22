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
        <c:if test="${not empty emptyCart }">
            <h1 class="">${emptyCart}</h1>
        </c:if>
        <c:if test="${empty emptyCart }">
            <div class="row">
                <c:forEach var="item" items="${cart.getCart()}">
                    <div class="col-lg-10 d-flex">
                        <div class="" style="width: 94px; height: 94px; margin-right: 5px">
                            <img src="${item.getProduct().getImgLink()}" alt="alt" class="rounded" style="width: 94px; height: 94px"/>
                        </div>
                        <div class="text-capitalize">
                            <h5 class="fw-light fst-italic">${item.getProduct().getName()}</h5>
                            <h6 class=" fw-lighter">${item.getProduct().getBrand()}</h6>
                        </div>
                    </div>
                    <div class="col-lg-2 d-flex flex-column justify-content-between align-items-center">
                        <p>$${item.getTotalCost()}</p>
                        <input type="number" value="${item.getQuantity()}" readonly="true" style="width: 30px" class="text-center">
                        <button style="background: transparent"><a href="url" class="text-decoration-none">Remove</a></button>
                    </div>
                    <hr style="margin-top: 12px">
                </c:forEach>                 
            </div>
        </c:if>
    </div>
    <div class="bottom d-flex flex-column" style="padding: 0 16px;margin-bottom: 16px">
        <div class="d-flex justify-content-between">
            <p>Total</p>
            <p>$${cart.getTotalCostCart()}</p>
        </div>
        <button class="btn btn-primary"><a href="url" class="text-white text-decoration-none">Checkout</a></button>
    </div>
</div>