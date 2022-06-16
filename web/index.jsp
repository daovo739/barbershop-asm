<%-- 
    Document   : index
    Created on : Jun 15, 2022, 10:23:44 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
    <body>

        <header class="w-100">
                    <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand"   href="#"><img src="./static/images/logo/logo.png" style="width: 100px"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="#">Home</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#products" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Products
                            </a>
                            <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
                                <c:forEach items="${categories}" var="item">
                                    <li><a class="dropdown-item text-capitalize" href="#">${item}</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                </c:forEach>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Booking</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link">About</a>
                        </li>
                    </ul>
                    <div class="d-flex">
                        <a class="btn btn-success" href="#" role="button" style="margin-right: 5px">Sign In</a>
                        <a class="btn btn-secondary" href="#" role="button">Sign Up</a>
                    </div>
                </div>
            </div>
        </nav>
            <div id="carouselExampleInterval" class="carousel slide carousel-fade w-100" data-bs-ride="carousel">
                <div class="carousel-inner h-100">
                    <div class="carousel-item active" data-bs-interval="1800">
                        <img src="./static/images/background/background3.jpg" class="d-block w-100 img-fluid" alt="...">
                    </div>
                    <div class="carousel-item h-100" data-bs-interval="1800">
                        <img src="./static/images/background/background1.jpg" class="d-block w-100 img-fluid"  alt="...">
                    </div>
                    <div class="carousel-item h-100">
                        <img src="./static/images/background/background2.jpg" class="d-block w-100 img-fluid" alt="...">
                    </div>
                </div>
            </div>
        </header>

        <main class="container" >
            <section class="section-product" id="products">
                <h1 class="section-title">Products</h1>
                <c:if test="${not empty msg}">
                    <h1 class="text-center">${msg}</h1>
                </c:if>
                <c:if test="${empty msg}">
                    <div id="carouselExampleControls" class="carousel slide" data-bs-ride="carousel"">
                        <div class="carousel-inner">
                                <c:set var="count" value="0"></c:set>
                                <c:set var="classCard" value="carousel-item active"></c:set>
                                <c:forEach begin="1" end="${numOfProducts}">
                                    <div class="${classCard}" >
                                        <div class="w-75 d-flex justify-content-between" style="margin: 0 auto">
                                        <c:forEach items="${products}" var="product" begin="${count}" end="${count+2}">
                                            <div class="col-lg-4 d-flex align-items-stretch" style="width: 14rem">
                                                <a href="#" class="card" style="width:100%; text-decoration: none;">
                                                    <img src="${product.getImgLink()}" class="card-img-top img-thumbnail" alt="..." >
                                                    <div class="card-body">
                                                        <h6 class="card-subtitle mb-2 text-muted text-center text-capitalize" style="font-size: 10px">${product.getBrand()}</h6>
                                                        <h5 class="card-title text-capitalize text-center" style="font-size: 12px">${product.getName()}</h5>
                                                    </div>
                                                </a>
                                            </div>
                                            <c:set var="count" value="${count+1}"></c:set>
                                           <c:set var="classCard" value="carousel-item"></c:set>
                                        </c:forEach>
                                    </div> 
                                           </div>
                                </c:forEach>               
                        </div>
                        <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="prev">
                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Previous</span>
                        </button>
                        <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls" data-bs-slide="next">
                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                            <span class="visually-hidden">Next</span>
                        </button>
                    </div>    
                </c:if>

            </section>
        </main>

    </body>
</html>
