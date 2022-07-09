<%-- 
    Document   : confirm-checkout
    Created on : Jun 29, 2022, 9:06:52 PM
    Author     : HHPC
--%>

<%@page import="model.History"%>
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
        <script src="https://www.paypal.com/sdk/js?client-id=AdznrYMJFJhdO3BsR0iQBUHNVyl5hZlQJqJ5UHA_xW9f9rlj2dEt4S9AFiKqvA_nZuAeJ5jdHMikrO25&disable-funding=credit,card"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp"/>
        <main>
            <section class="container">
                <c:if test="${not empty checkoutSuccess}">
                    <div class="d-flex flex-column justify-content-center align-items-center text-white" style="height: 70vh">
                        <span class="material-symbols-outlined" style="font-size: 200px">
                        local_shipping
                    </span>
                    <h1>${checkoutSuccess}</h1>
                    <a href="GetProductsHomeServlet" class="btn btn-success mt-3 text-decoration-none text-capitalize" style="font-size: 20px">Back to HOME page</a>
                    </div>
                    
                </c:if>
                <c:if test="${empty checkoutSuccess}">
                    <form class="row" action="confirm-checkout" method="POST">
                    <div class="col-lg-6">
                        <h3 class="text-white text-capitalize mb-3 text-center">Order summary</h3>
                        <div class="checkout-products d-flex flex-column justify-content-between">
                            <div class="d-flex flex-column " style="height: 500px; overflow-y:  scroll; overflow-x: hidden">
                                <c:forEach var="item" items="${cartConfirm.getCart()}">
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
                                            <input type="number"  value="${item.getQuantity()}" style="width: 36px" class="text-center" readonly="true">
                                            <p class="text-white">$${item.getTotalCost()}</p>
                                        </div>
                                    </div>
                                    <hr style="color: #fff" class="mt-3">
                                </c:forEach>
                            </div>
                            <div class="d-flex flex-column text-white mt-3">
                                <div class="d-flex justify-content-between">
                                    <h5>Subtotal</h5>
                                    <p">$${historyConfirm.getTotalCostHistory()}</p>
                                </div>
                                <div class="d-flex justify-content-between">
                                    <h5>Shipping</h5>
                                    <c:if test="${historyConfirm.getDeliveryMethod() == 'standard'}">
                                        <c:set  var="shipping" value="2.00" />
                                        <p>$2.00</p>
                                    </c:if>
                                    <c:if test="${historyConfirm.getDeliveryMethod() == 'express'}">
                                        <c:set  var="shipping" value="6.00" />
                                        <p>$6.00</p>
                                    </c:if>
                                </div>
                                <div class="d-flex justify-content-between mt-3">
                                    <h4>Total</h4>
                                    <h5 class="totalCost" data-price="${historyConfirm.getTotalCostHistory()}">$${historyConfirm.getTotalCostHistory()}</h5>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="col-lg-6">

                        <h3 class="text-white text-capitalize text-center">receiver's information</h3>
                        <div class="f-flex flex-column text-white mt-3">

                            <h5>Emaill address: ${historyConfirm.getEmail()}</h5>
                            <h5>Phone number: ${historyConfirm.getPhone()}</h5>
                            <h5 class="text-capitalize">Receiver: ${historyConfirm.getName()}</h5>
                            <h5 class="text-capitalize">Address: ${historyConfirm.getAddress()}</h5>
                            <h5 class="text-capitalize">City: ${historyConfirm.getCity()}</h5>
                            <h5 class="text-capitalize">District: ${historyConfirm.getDistrict()}</h5>
                            <h5 class="text-capitalize">Ward: ${historyConfirm.getWard()}</h5>
                            <h5 class="text-capitalize">Delivery Method:  ${historyConfirm.getDeliveryMethod()}</h5>
                            <h5 class="text-capitalize">Payment method: ${historyConfirm.getPaymentMethod()}</h5>
                        </div>
                            <div class="d-flex flex-column">                      
                                <c:if test="${historyConfirm.getPaymentMethod() == 'cash'}">
                                    <input type="submit" class="btn btn-primary text-center mt-5 w-100 text-uppercase" style="padding: 6px; font-size: 20px; letter-spacing: 2px" value="Confirm"/>
                                </c:if>
                                <c:if test="${historyConfirm.getPaymentMethod() == 'paypal'}">
                                    <div class="d-flex text-white mt-3 justify-content-center ">
                                        <div id="paypal" class="w-100"></div>
                                    </div>
                                </c:if>
                                <a href="checkout" class="btn btn-danger text-center mt-2 w-100 text-uppercase" style="padding: 6px; font-size: 20px; letter-spacing: 2px">Cancel</a>
                            </div>
                    </div>             
                </form>
                </c:if>

            </section>
            <form action="confirm-checkout" method="POST" style="visibility: hidden" id="form-hidden"></form>
        </main>
        <jsp:include page="layout/footer.jsp"/>


        <script>
            const totalCost = +document.querySelector(".totalCost").getAttribute("data-price");
           const paypalButtonsComponent = paypal.Buttons({
              // optional styling for buttons
              // https://developer.paypal.com/docs/checkout/standard/customize/buttons-style-guide/
              style: {
                color: "blue",
                shape: "rect",
                layout: "vertical"
              },

              // set up the transaction
              createOrder: (data, actions) => {
                  // pass in any options from the v2 orders create call:
                  // https://developer.paypal.com/api/orders/v2/#orders-create-request-body
                  const createOrderPayload = {
                      purchase_units: [
                          {
                              amount: {
                                  value: totalCost
                              }
                          }
                      ]
                  };

                  return actions.order.create(createOrderPayload);
              },

              // finalize the transaction
              onApprove: (data, actions) => {
                  
                  const captureOrderHandler = (details) => {
                      const payerName = details.payer.name.given_name;    
                      console.log('Transaction completed');
                      document.getElementById("form-hidden").submit();
                  };

                  return actions.order.capture().then(captureOrderHandler);
              },

              // handle unrecoverable errors
              onError: (err) => {
                  console.error('An error prevented the buyer from checking out with PayPal');
              }
          });

          paypalButtonsComponent
              .render("#paypal")
              .catch((err) => {
                  console.error('PayPal Buttons failed to render');
              });
        </script>
    </body>
</html>
