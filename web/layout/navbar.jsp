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
                                    <a class="nav-link text-white fs-4"  href="#">${cookie.userName.value} </a>
                                </div>
                                <ul class="dropdown-menu dropdown-menu-dark" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="#">View your cart</a></li>
                                    <li><hr class="dropdown-divider"></li>
                                    <li>
                                        <a class="btn btn-danger nav-link d-flex text-white " href="logout" ><span class="material-symbols-outlined">
                                                logout</span> Sign Out</a> </li>
                                </ul>
                            </div>
                            <div class="cart-icon-container">
                                <button class="btn btn-sidebar" type="button" data-bs-toggle="offcanvas" 
                                        data-bs-target="#offcanvasWithBothOptions" aria-controls="offcanvasWithBothOptions" onclick="getCart()">
                                    <span class="material-symbols-outlined" style="color: #F9F2ED; font-size: 40px"> shopping_cart</span>
                                </button>
                                <div class="text-white text-center cart-count">
                                    <p class="w-100 h-100">${cartCount}</p>
                                </div>

                            </div>

                        </div>

                    </c:if>
                </div>
            </div>
        </nav>
        <jsp:include page="cartSidebar.jsp"/>
        <!--TOAST-->
        <div class="position-fixed top-25 end-0 p-3" style="z-index: 11" >
            <div id="liveToast" class="toast " role="alert" aria-live="assertive" aria-atomic="true" data-bs-delay="1000">
                <div class="toast-header ">
                    <span class="material-symbols-outlined text-success">
                        check
                    </span>
                    <strong class="me-auto text-success">Notification</strong>
                    <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                </div>
                <div class="toast-body">
                    Add product to cart successfully!
                </div>
            </div>
        </div>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                                            const getCart = () => {
                                                $.ajax({
                                                    url: "/barbershop/GetCartServlet",
                                                    type: "GET",
                                                    success: function (results) {
//                            console.log(results);
                                                        document.querySelector(".offcanvas-insert").innerHTML = results;
                                                    },
                                                    error: function (error) {
                                                        console.log(error);
                                                    }
                                                });
                                            };

                                            const deleteProduct = (product) => {
                                                const id = product.getAttribute("data-product-id");
                                                $.ajax({
                                                    url: "/barbershop/addToCart" + "?" + $.param({id}),
                                                    type: "DELETE",
                                                    success: function (results) {
//                            console.log(results);
                                                        document.querySelector(".offcanvas-insert").innerHTML = results;
                                                    },
                                                    error: function (error) {
                                                        console.log(error);
                                                    }
                                                });
                                                const cartCountEle = document.querySelector('.cart-count');
                                                cartCountEle.textContent = parseInt(cartCountEle.textContent) - 1;
                                            };

                                            const changeQuantity = (inputEle) => {
                                                const quantity = inputEle.value;
                                                const id = inputEle.getAttribute('data-product-id');
                                                $.ajax({
                                                    url: "/barbershop/addToCart" + "?" + $.param({quantity, id}),
                                                    type: "PUT",
                                                    success: function (results) {
//                            console.log(results);
                                                        document.querySelector(".offcanvas-insert").innerHTML = results;
                                                    },
                                                    error: function (error) {
                                                        console.log(error);
                                                    }
                                                });

                                            };
        </script>
    </body>

</html>
