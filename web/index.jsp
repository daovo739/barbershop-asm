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
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <div class="container">
                <a class="navbar-brand d-lg-none"href="#"><img src="./static/images/logo/logo.png" style="width: 100px"></a>
                <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#myNavbarToggler7"
                        aria-controls="myNavbarToggler7" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="myNavbarToggler7">
                    <ul class="navbar-nav mx-auto">
                        <li class="nav-item">
                            <a class="nav-link" href="#">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">About</a>
                        </li>
                        <a class="navbar-brand d-none d-lg-block"   href="#"><img src="./static/images/logo/logo.png" style="width: 80px"></a>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Services</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#">Contact</a>
                        </li>
                    </ul>
                </div>
            </div>
        </nav>

        <section class="w-100">
            <div id="carouselExampleInterval" class="carousel slide w-100" data-bs-ride="carousel" style="height: 90vh">
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
        </section>

        <main class="container">
            <section class="section-product">
                <h1 class="section-title text-center">Products</h1>
                <c:if test="${not empty msg}">
                    <h1 class="text-center">${msg}</h1>
                </c:if>
                <c:if test="${empty msg}">
                    <div class="row">
                    <c:forEach items="${products}" var="product">
                        <div class="col-lg-4">
                        <div class="card" style="width: 16rem;">
                            <img src="${product.getImgLink()}" class="card-img-top img-thumbnail" alt="..." >
                            <div class="card-body">
                                 <h6 class="card-subtitle mb-2 text-muted text-center text-capitalize">${product.getBrand()}</h6>
                                <h5 class="card-title text-capitalize">${product.getName()}</h5>
                                <p class="card-text">${product.getPrice()}</p>
                                <a href="#" class="btn btn-primary">Go somewhere</a>
                            </div>
                        </div>
                    </div>
                    </c:forEach>
                </div>
                    
                </c:if>
                
            </section>
        </main>

    </body>
</html>
