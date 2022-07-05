<%-- 
    Document   : history
    Created on : Jul 2, 2022, 3:54:32 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./static/css/login-register.css" rel="stylesheet" type="text/css"/>
        <title>History Page</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp"/>
        <main>
            <section class="container" style="min-height: 80vh">
                <div class="row">
                    <div class="col-lg-4">
                        <div class="d-flex flex-column text-white border" style="padding: 32px 12px; margin-top: 96px">
                            <div class="d-flex justify-content-center align-items-center profile-img w-75" style="margin: 0 auto">
                                <img src="${userCurrent.getAvatar()}" alt="alt" class="img-fluid w-100" />
                                <button class="edit-profile-btn_img" data-bs-toggle="modal" data-bs-target="#avatarModal">Change avatar</button>
                            </div>
                            <div class="d-flex justify-content-center align-items-center">
                                <h4 class="text-capitalize text-center mt-2">${userCurrent.getName()}</h4>
                                <button class="material-symbols-outlined ms-2 edit-profile-btn" style="font-size: 20px" data-bs-toggle="modal" data-bs-target="#nameModal">
                                    edit
                                </button>
                            </div>
                            <h6 class="mt-2">The number of orders: <strong>${totalOrder}</strong></h6>
                            <h6>Total amount ordered: <strong>$${totalCostOrder}</strong></h6>
                        </div>
                    </div>

                    <div class="col-lg-8">
                        <h1 class="section-title">Order history</h1>
                        <c:if test="${not empty msg}">
                            <div class="text-white d-flex flex-column">
                                <h2>${msg}</h2>
                                <a class="btn btn-primary" type="button" href="products">Shopping Now!</a>
                            </div>
                            
                        </c:if>
                        <c:if test="${empty msg}">
                            <div class="accordion" id="accordionExample">
                                
                                
                                <c:forEach var="history" items="${histories}">
                                    <c:if test="${history.getDeliveryMethod() == 'standard'}">
                                        <c:set var="delivery" value="2.0"/>
                                    </c:if>
                                    <c:if test="${history.getDeliveryMethod() == 'express'}">
                                        <c:set var="delivery" value="$6.0"/>
                                    </c:if>
                                    <div class="accordion-item">
                                        <div class="accordion-header d-flex flex-column" id="headingOne">
                                            <div class="accordion-button collapsed d-flex flex-column align-items-stretch"  type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${history.getHistoryId()}" aria-expanded="false" aria-controls="collapse-${history.getHistoryId()}">
                                                <h3>Order #<strong>${history.getCreateAt()}</strong></h3>
                                                <p>Receiver: <strong> <span class="text-capitalize">${history.getName()} </span> | ${history.getPhone()} | ${history.getEmail()}</strong></p>
                                                <p>Address: <strong class="text-capitalize">${history.getAddress()} , ${history.getCity()}, ${history.getDistrict()}, ${history.getWard()}</strong></p>
                                                <p>Checkout Method: <strong class="text-capitalize">${history.getPaymentMethod()}</strong> | Delivery Method: <strong class="text-capitalize">${history.getDeliveryMethod()} (${delivery})</strong></p>
                                                <p >Total: <strong>$${history.getTotalCostHistory()}</strong></p>
                                            </div>

                                        </div>
                                        <div id="collapse-${history.getHistoryId()}" class="accordion-collapse collapse" aria-labelledby="${history.getHistoryId()}" data-bs-parent="#accordionExample">
                                            <div class="accordion-body">
                                                <c:forEach var="item" items="${history.getProductsList()}">
                                                    <div class = "d-flex justify-content-between">
                                                        <div class="d-flex">
                                                            <div class="" style="width: 60px; height: 60px; margin-right: 10px">
                                                                <img src="${item.key.getImgLink()}" alt="alt" class="rounded" style="width: 120px; height: 120px">
                                                            </div>
                                                            <div class="ms-5">
                                                                <h5 class="fw-light fst-italic text-capitalize ">${item.key.getName()}</h5>
                                                                <h6 class=" fw-lighter text-capitalize ">${item.key.getBrand()}</h6>
                                                                <h6 class=" fw-bolder text-capitalize ">$${item.key.getPrice()}</h6>
                                                                <p >x${item.value}</p>
                                                            </div>
                                                        </div>
                                                        <div class=" d-flex flex-column me-5" style="height: 120px">
                                                            <fmt:formatNumber var="total" value="${item.key.getPrice() * item.value}" maxFractionDigits="2"/>
                                                            <p class="">Subtotal: <strong>$${total}</strong></p>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>  
                            </div>
                        </div>
                    </c:if>

                </div>
            </section>
        </main>
        <jsp:include page="layout/footer.jsp"/>

        <div class="modal fade" id="avatarModal" tabindex="-1" aria-labelledby="avatarModal" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Change avatar</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body" ">
                        <form action="history" method="POST" enctype='multipart/form-data'>
                            <div class="mb-3">
                                <input type="file" class="form-control" id="newAvatar" name="newAvatar" required>
                            </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-primary">Save changes</button>
                    </div>
                    </form>
                </div>
            </div>
        </div>

        <div class="modal fade" id="nameModal" tabindex="-1" aria-labelledby="nameModal" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Change avatar</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="history" method="POST" enctype='multipart/form-data'">
                        <div class="modal-body">
                           <div class="mb-3">
                               <input type="text" class="form-control" id="exampleFormControlInput1" placeholder="Enter new name" name="newName" required>          
                            </div>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Save changes</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </body>
</html>

