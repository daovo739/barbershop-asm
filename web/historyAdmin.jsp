<%-- 
    Document   : historyAdmin
    Created on : Jul 4, 2022, 9:32:27 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Orders</title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="wrapper d-flex align-items-stretch">
            <jsp:include page="/layout/admin/navbarAdmin.jsp"/>
            <div class="w-100" style="padding: 28px; margin-left: 280px;" >
                <h1 class="section-title bg-white text-dark"style="padding: 0; margin-bottom: 0">History Orders</h1>
                <form action="historyAdmin" method="GET" class="d-flex  align-items-end w-50 mb-3">
                        <div class="d-flex flex-column me-3">
                            <label for="history-admin-from">From</label>
                            <input type="date" class="form-control" name="history-admin-from" id="history-admin-from">
                        </div>
                        <div class="d-flex flex-column">
                            <label for="history-admin-to">To</label>
                            <input type="date" class="form-control" name="history-admin-to" id="history-admin-to">
                        </div>
                        <div class="d-flex align-items-center ms-3">
                            <input type="submit" class="btn btn-primary text-decoration-none me-2" />
                            <a href="historyAdmin" type="button" class="btn btn-danger text-decoration-none">Unfiltered</a>
                        </div>
                    </form>
                <c:if test="${not empty msg}">
                    <h2 class="text-center">${msg}</h2>
                </c:if>
                <c:if test="${ empty msg}">

                    <c:forEach var="history" items="${histories}">
                        <div class="accordion-item">
                            <div class="accordion-header d-flex" id="headingOne">
                                <div class="d-flex flex-column mt-5" style="width: 250px" >    
                                    <img src="${history.getUser().getAvatar()}" alt="alt" class="img-fluid w-100" style="height: 150px"/>
                                    <h4 class="text-center mt-2">User <strong>#${history.getUserId()}</strong>:${history.getUser().getName() }</h4>
                                </div>
                                <div class=" accordion-button collapsed d-flex flex-column align-items-stretch"  type="button" data-bs-toggle="collapse" data-bs-target="#collapse-${history.getHistoryId()}" aria-expanded="false" aria-controls="collapse-${history.getHistoryId()}">
                                    <h3>Order #<strong>${history.getHistoryId()}: ${history.getCreateAt()}</strong></h3>
                                    <p>Receiver: <strong> <span class="text-capitalize">${history.getName()} </span> | ${history.getPhone()} | ${history.getEmail()}</strong></p>
                                    <p>Address: <strong class="text-capitalize">${history.getAddress()} , ${history.getCity()}, ${history.getDistrict()}, ${history.getWard()}</strong></p>
                                    <p>Checkout Method: <strong class="text-capitalize">${history.getPaymentMethod()}</strong> | Delivery Method: <strong class="text-capitalize">${history.getDeliveryMethod()}</strong></p>
                                    <p>Total: <strong>$${history.getTotalCostHistory()}</strong></p>
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
            </c:if>

        </div>
    </body>
</html>
