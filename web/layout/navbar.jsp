<%-- 
    Document   : navbar
    Created on : Jun 17, 2022, 1:12:46 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
         <link rel="stylesheet" href="https://fonts.googleapis.com/css2?family=Material+Symbols+Outlined:opsz,wght,FILL,GRAD@20..48,100..700,0..1,-50..200" />
    </head>
    <body>
        <c:set var="classNav" value="navbar navbar-expand-lg navbar-dark fixed-top"></c:set>
        <c:if test="${pageContext.request.servletPath != '/index.jsp'}">
            <c:set var="classNav" value="navbar navbar-expand-lg navbar-dark "></c:set>
        </c:if>
        <nav class="${classNav}">
            <div class="container">
                <a class="navbar-brand"   href="#"><img src="./static/images/logo/logo.png" style="width: 100px"></a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        <li class="nav-item">
                            <a class="nav-link active" aria-current="page" href="GetProductsHomeServlet">Home</a>
                        </li>
                        <c:if test="${pageContext.request.servletPath != '/products.jsp'}">
                            <li class="nav-item dropdown">
                                <a class="nav-link dropdown-toggle" href="index.jsp#products" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    Products
                                </a>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdown">
                                    <c:forEach items="${categories}" var="item">
                                        <li><a class="dropdown-item text-capitalize" href="products?category=${item}">${item}</a></li>
                                        <li><hr class="dropdown-divider"></li>
                                        </c:forEach>
                                </ul>
                            </li>
                        </c:if>

                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp#booking">Booking</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="index.jsp#about">About</a>
                        </li>
                    </ul>
                    <c:if test="${not cookie.containsKey('userName')}">
                        <div class="d-flex">
                            <a class="btn btn-success" href="login.jsp" role="button" style="margin-right: 5px">Sign In</a>
                            <a class="btn btn-secondary" href="register.jsp" role="button">Sign Up</a>
                        </div>
                    </c:if>
                    <c:if test="${cookie.containsKey('userName')}">
                        <div class="d-flex align-items-center justify-content-center">
                            <div class="nav-item dropdown">
                                <div class="nav-link dropdown-toggle d-flex align-items-center" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <span class="material-symbols-outlined" style="color: green; font-size: 30px">account_circle</span>
                                    <a class="nav-link text-white fs-4"  href="#">${userLogin} </a>
                                </div>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="#">View your cart</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li>
                                        <a class="btn btn-danger nav-link d-flex text-white " href="logout" ><span class="material-symbols-outlined">
                                                logout</span> Sign Out</a> </li>
                                </ul>
                            </div>
                            <button class="btn btn-sidebar" type="button" data-bs-toggle="offcanvas" 
                                    data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions">
                                <span class="material-symbols-outlined" style="color: #F9F2ED; font-size: 30px">
                                    shopping_cart
                                </span></button>
                        </div>

                    </c:if>
                    </div>
                </div>
            </nav>
            <jsp:include page="cartSidebar.jsp"/>
    </body>
</html>
