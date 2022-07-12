<%-- 
    Document   : dashboard
    Created on : Jun 25, 2022, 2:13:12 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dashboard</title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <div class="wrapper d-flex align-items-stretch" style="height: 100vh">
            <jsp:include page="/layout/admin/navbarAdmin.jsp"/>
            <div class="w-100" style="padding: 12px; margin-left: 280px;" >
                <h1 class="section-title bg-white text-dark"style="padding: 30px; margin-bottom: 0">Dashboard</h1>
                <section class="h-75">
                    <div class="row h-100">
                        <div class="col-lg-8 d-flex flex-column">
                            <div class="card h-50">

                                <div class="card-body">
                                    <form action="dashboardAdmin" method="GET" class="d-flex  justify-content-center align-items-end mb-5">
                                        <div class="d-flex flex-column me-3">
                                            <label for="dashboard-admin-from">From</label>
                                            <input type="date" class="form-control" name="dashboard-from" id="dashboard-admin-from">
                                        </div>
                                        <div class="d-flex flex-column">
                                            <label for="dashboard-admin-to">To</label>
                                            <input type="date" class="form-control" name="dashboard-to" id="dashboard-admin-to">
                                        </div>
                                        <div class="d-flex align-items-center ms-3">
                                            <input type="submit" class="btn btn-primary text-decoration-none me-2" value="Filter"/>
                                            <a href="historyAdmin" type="button" class="btn btn-danger text-decoration-none">Unfiltered</a>
                                        </div>
                                    </form>
                                    <div class="d-flex align-items-center justify-content-center ">
                                        <div class="me-5 d-flex flex-column align-items-center">
                                            <div class=" d-flex align-items-center">
                                                <p class="subtitle text-sm text-muted mb-0 text-uppercase" style="letter-spacing: 2px; font-size: 40px">Earnings</p>
                                                <span class="material-symbols-outlined" style="color: #dc3545;font-size: 40px">
                                                    paid
                                                </span>
                                            </div>
                                            <p>02-06-2002 : 06-07-2022</p>
                                        </div>
                                        <div class="d-flex justify-content-end me-5">
                                            <h1 class="fw-normal text-red" style="color: #dc3545">$${dashboard.getTotalCost()}</h1>
                                        </div>
                                    </div>

                                </div>
                                <div class="card-footer py-3" style="background: #dc3545">
                                    <div class="row align-items-center text-red">
                                        <div class="col-10">
                                        </div>
                                        <div class="col-2 text-end"><i class="fas fa-caret-up"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>  
                            <div class="d-flex justify-content-between mt-5" ">
                                <div class="card w-100 h-75"  style="width: 18rem;">
                                    <div class="card-header d-flex  h-75">
                                        <img src="${dashboard.getBestSale().getProduct().getImgLink()}" class="h-100 img-fluid" alt="..." >
                                        <div class="d-flex flex-column align-items-center h-75 mt-5 w-100">
                                            <p class="subtitle text-sm text-muted mb-0 text-uppercase" style="letter-spacing: 2px; font-size: 24px">Best sale</p>
                                            <p class="fs-4 text" style="color: #fd7e14">${dashboard.getBestSale().getQuantity()}</p>
                                        </div>
                                    </div>            
                                    <div class="card-body d-flex justify-content-between fs-6 text">
                                        <p class="card-text">${dashboard.getBestSale().getProduct().getName()} </p>
                                        <p class="card-text text-capitalize">| ${dashboard.getBestSale().getProduct().getBrand()}</p>
                                        <p class="card-text">| $${dashboard.getBestSale().getProduct().getPrice()}</p>
                                    </div>
                                </div>
                                <div class="d-flex flex-column justify-content-between w-75 h-75 ms-3">
                                    <div class="card h-100">
                                        <div class="card-body">
                                            <div class="d-flex align-items-center justify-content-between">
                                                <div>
                                                    <h4 class="fw-normal" style="color: #20c997">${dashboard.getUserCount()}</h4>
                                                    <p class="subtitle text-sm text-muted mb-0 text-uppercase" style="letter-spacing: 2px; font-size: 20px">users</p>
                                                </div>
                                                <div class="flex-shrink-0 ms-3">
                                                    <span class="material-symbols-outlined" style="color: #20c997; font-size: 30px" >
                                                        group
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer py-3" style="background: #20c997">
                                            <div class="row align-items-center">
                                                <div class="col-10">
                                                    <p class="mb-0"></p>
                                                </div>
                                                <div class="col-2 text-end"><i class="fas fa-caret-up"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>  
                                    <div class="card h-100 mt-2">
                                        <div class="card-body">
                                            <div class="d-flex align-items-center justify-content-between">
                                                <div>
                                                    <h4 class="fw-normal" style="color: #0d6efd">${dashboard.getHistoryCount()}</h4>
                                                    <p class="subtitle text-sm text-muted mb-0 text-uppercase" style="letter-spacing: 2px; font-size: 20px">orders</p>
                                                </div>
                                                <div class="flex-shrink-0 ms-3">

                                                    <span class="material-symbols-outlined" style="color: #0d6efd ; font-size: 30px">
                                                        history
                                                    </span>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="card-footer py-3" style="background: #0d6efd">
                                            <div class="row align-items-center text-red">
                                                <div class="col-10">
                                                    <p class="mb-0"></p>
                                                </div>
                                                <div class="col-2 text-end"><i class="fas fa-caret-up"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>  
                                </div>


                            </div>
                        </div>
                        <div class="col-lg-4 d-flex flex-column">
                            <div class="card">
                                <div class="card-body">
                                    <div class="d-flex align-items-center justify-content-between">
                                        <div>
                                            <h4 class="fw-normal" style="color: #6f42c1">${dashboard.getBookingCount()}</h4>
                                            <p class="subtitle text-sm text-muted mb-0 text-uppercase" style="letter-spacing: 2px; font-size: 24px">bookings</p>
                                        </div>
                                        <div class="flex-shrink-0 ms-3">
                                            <span class="material-symbols-outlined" style="color: #6f42c1 ; font-size: 40px">
                                                menu_book
                                            </span>
                                        </div>
                                    </div>
                                </div>
                                <div class="card-footer py-3 " style="background:  #6f42c1">
                                    <div class="row align-items-center" ">
                                        <div class="col-10" >
                                            <p class="mb-0"></p>
                                        </div>
                                        <div class="col-2 text-end"><i class="fas fa-caret-up"></i>
                                        </div>
                                    </div>
                                </div>
                            </div>  

                            <div class="card mt-3" style="height: 495px; max-height: 495px;">
                                <div class="card-header">
                                    <p class="text-uppercase fs-5 text text-center" style="letter-spacing: 2px; margin-bottom: 0">Bookings</p>
                                    <p class=" text-uppercase fs-6 text text-center">today $ tomorrow</p>
                                </div>
                                <div class="card-body" style="overflow-y: scroll">
                                    <c:if test="${not empty msg}">
                                        <div class="d-flex justify-content-center align-items-center">
                                            <h5>${msg}</h5>
                                        </div>
                                    </c:if>
                                    <c:if test="${empty msg}">
                                        <div class="d-flex flex-column" >
                                            <c:forEach var="item" items="${bookings}">
                                                <div class="d-flex justify-content-between align-items-center">
                                                    <div>
                                                        <p class="text-capitalize">${item.getBookingName()}</p>
                                                    </div>
                                                    <div>
                                                        <p>${item.getBookingDate()}</p>
                                                    </div>
                                                </div>
                                                    <hr>
                                            </c:forEach>
                                        </div>
                                    </c:if>
                                </div>
                                <div class="d-flex justify-content-center" style="margin: 8px 0">
                                    <a href="bookingAdmin" type="button" class="btn btn-success text-decoration-none">View All Bookings</a>
                                </div>
                            </div> 

                        </div>
                </section>
            </div>
        </div>
    </body>
</html>
