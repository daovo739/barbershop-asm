<%-- 
    Document   : checkout
    Created on : Jun 29, 2022, 9:06:52 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Checkout</title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://www.paypal.com/sdk/js?client-id=AdznrYMJFJhdO3BsR0iQBUHNVyl5hZlQJqJ5UHA_xW9f9rlj2dEt4S9AFiKqvA_nZuAeJ5jdHMikrO25"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
         <jsp:include page="layout/navbar.jsp"/>
        <main>
            <section class="container">
                <form class="row" action="checkout" method="POST">
                    <div class="col-lg-6">
                        <div>
                            <h3 class="text-white text-capitalize">Contact information</h3>
                            <div class="d-flex flex-column text-white ">
                                <label for="checkout-email " class="form-label">Email address</label>
                                <input type="text" id="checkout-email" value="${user.getEmail()}" name="checkout-email" class="form-control" required>
                            </div>
                            <div class="d-flex flex-column text-white mt-2">
                                <label for="checkout-phone"  class="form-label" >Phone number</label>
                                <input type="text" id="checkout-phone" class="form-control"name="checkout-phone" required>
                            </div>
                        </div>
                        <div class="mt-5">
                            <h3 class="text-white text-capitalize">Shipping information</h3>
                            <div class="d-flex flex-column text-white ">
                                <label for="checkout-name"  class="form-label ">Your name</label>
                                <input type="text" id="checkout-name " value="${user.getName()}" name="checkout-name" class="form-control text-capitalize" required>
                            </div>

                            <div class="mt-3 d-flex flex-column  align-items-start"  >
                                <select  name="checkout-city" id="cities" class="form-select " required>
                                    <option value="" selected>Choose the city</option>
                                </select>
                                <select  name="checkout-district" id="districts" class="form-select mt-2 " required>
                                    <option value="" selected>Choose the district</option>
                                </select>
                                <select  name="checkout-ward" id="wards" class="form-select mt-2" required>
                                    <option value="" selected>Choose the wards</option>
                                </select>
                            </div>
                            <div class="d-flex flex-column text-white mt-2">
                                <label for="checkout-address"  class="form-label">Address</label>
                                <input type="text" id="checkout-address" class="form-control" name="checkout-address" required>
                            </div>
                        </div>
                        <div class="mt-5">
                            <h3 class="text-white text-capitalize">Delivery method</h3>
                            <div class="d-flex flex-column text-white ">
                                <div class="form-check">
                                    <input class="form-check-input checkout-method" type="radio" name="checkout-delivery" id="flexRadioDefault1" data-payment-price="$2.00" value="standard" required>
                                    <label class="form-check-label" for="flexRadioDefault1">
                                        Standard (4-10 business days) <strong>$2.00</strong>
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input checkout-method" type="radio" name="checkout-delivery" id="flexRadioDefault2" data-payment-price="$6.00" value="express" checked required>
                                    <label class="form-check-label" for="flexRadioDefault2">
                                        Express (2-5 business days) <strong>$6.00</strong>
                                    </label>
                                </div>
                            </div>
                        </div>
                            <div class="mt-5">
                            <h3 class="text-white text-capitalize">Payment method</h3>
                             <div class="form-check">
                                    <input class="form-check-input checkout-choose-method" id="cash-method" type="radio" name="checkout-method"  value="cash" required>
                                    <label class="form-check-label text-white" for="cash-method">
                                        Payment in cash
                                    </label>
                                </div>
                                <div class="form-check">
                                    <input class="form-check-input checkout-choose-method" id="paypal-method" type="radio" name="checkout-method" value="paypal" required checked>
                                    <label class="form-check-label text-white" for="paypal-method">
                                        Payment in paypal
                                    </label>
                                </div>
<!--                            <div class="d-flex flex-column text-white ">
                                <div id="paypal" class="w-75"></div>
                            </div>-->
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <h3 class="text-white text-capitalize mb-3">Order summary</h3>
                        <div class="checkout-products d-flex flex-column justify-content-between">
                            <div class="d-flex flex-column " style="height: 500px; overflow-y:  scroll; overflow-x: hidden">
                                <c:forEach var="item" items="${cart.getCart()}">
                                    <div class="row">
                                        <div class="col-lg-10 d-flex">
                                            <div class="" style="width: 94px; height: 94px; margin-right: 5px">
                                                <img src="${item.getProduct().getImgLink()}" alt="alt" class="rounded" style="width: 120px; height: 120px">
                                            </div>
                                            <div class="text-capitalize text-white ms-5">
                                                <h5 class="fw-light fst-italic ">${item.getProduct().getName()}</h5>
                                                <h6 class=" fw-lighter">${item.getProduct().getBrand()}</h6>
                                                <h6 class=" fw-bolder">$${item.getProduct().getPrice()}</h6>
                                            </div>
                                        </div>
                                        <div class="col-lg-2 d-flex flex-column justify-content-between align-items-center" style="height: 120px">
                                            <button style="background: transparent"><a data-product-id="${item.getProduct().getId()}" class="text-decoration-none" onclick="deleteProductCheckout(this)">Remove</a></button>
                                            <input type="number" data-product-id="${item.getProduct().getId()}" value="${item.getQuantity()}" style="width: 36px" class="text-center" onclick="changeQuantityCheckout(this)">
                                            <p class="text-white">$${item.getTotalCost()}</p>
                                        </div>
                                    </div>
                                    <hr style="color: #fff" class="mt-5">
                                </c:forEach>
                            </div>
                            <div class="d-flex flex-column text-white mt-5">
                                <div class="d-flex justify-content-between">
                                    <h5>Subtotal</h5>
                                    <p class="checkout-cart-total">$${cart.getTotalCostCart()}</p>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <h5>Shipping</h5>
                                    <p class="checkout-fee-shipping"></p>
                                </div>
                                <div class="d-flex justify-content-between mt-3">
                                    <h4>Total</h4>
                                    <h5 class="checkout-total"></h5>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex">                      
                            <input type="submit" class="btn btn-primary text-center mt-5 w-100 text-uppercase" style="padding: 6px; font-size: 20px; letter-spacing: 2px" value="Checkout"/>
                        </div>
                    </div>
                </form>
            </section>

        </main>
        <jsp:include page="layout/footer.jsp"/>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
    <script src="./script/scriptCheckout.js"></script>
</html>
