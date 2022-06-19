<%-- 
    Document   : index
    Created on : Jun 15, 2022, 10:23:44 PM
    Author     : HHPC
--%>

<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp"/>
        <header class="w-100" style="position: relative">
            <div id="carouselExampleInterval" class="carousel slide carousel-fade w-100" data-bs-ride="carousel" >         
                <a href="#booking" class="carousel-btn">Booking now</a>
                <div class="carousel-inner h-100">
                    <div class="carousel-item active" data-bs-interval="1800">
                        <img src="./static/images/background/background3.jpg" class="d-block w-100 img-fluid" alt="..." style="object-fit:cover">
                    </div>
                    <div class="carousel-item h-100" data-bs-interval="1800">
                        <img src="./static/images/background/background1.jpg" class="d-block w-100 img-fluid"  alt="..." style="object-fit:cover">
                    </div>
                    <div class="carousel-item h-100">
                        <img src="./static/images/background/background2.jpg" class="d-block w-100 img-fluid" alt="..." style="object-fit:cover">
                    </div>
                </div>
            </div>
        </header>

        <main class="container-fluid" >
            <section class="section-product" id="products" style="border: none">
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
                                                <a href="product?id=${product.getId()}" class="card bg-dark text-white " style="width:100%; text-decoration: none;">
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
                    <div class="w-100 d-flex justify-content-center" style="margin-top: 20px">          
                        <a class="btn btn-primary btn-lg" href="products?category=all" role="button" >View All Products</a>
                    </div>

                </c:if>
            </section>


            <section class="section-booking" id="booking">
                <h1 class="section-title">Booking</h1>
                <form class="row" action="#" method="POST">
                    <div class="col-lg-6">
                        <div class="mb-3">
                            <input type="text" class="form-control bg-dark text-white" id="formGroupExampleInput" placeholder="Enter your phone number" name="booking-phone" required>
                        </div>
                        <div class="mb-3">
                            <input type="text" class="form-control bg-dark text-white" id="formGroupExampleInput2" placeholder="Enter your name" name="booking-name" required>
                        </div>
                        <select class="form-select  mb-3 bg-dark text-white" aria-label=".form-select-lg example" name="booking-service" required>
                            <option value="">Choose Service</option>
                            <option value="combo">Full Combo</option>
                            <option value="cutAndWash">Cut + Wash Hair</option>
                            <option value="cut">Cut Hair</option>
                        </select>
                        <div class="mb-3">
                            <input type="date" class="form-control bg-dark text-white" id="formGroupExampleInput2" placeholder="Enter booking date" name="booking-date" required>
                        </div>
                        <div class="form-floating">
                            <textarea class="form-control bg-dark text-white" placeholder="Leave a comment here" id="floatingTextarea2" style="height: 100px" name="booking-note"></textarea>
                            <label for="floatingTextarea2" class="text-white">Note</label>
                        </div>
                    </div>
                    <div class="col-lg-6 d-flex flex-column justify-content-evenly">
                        <c:set var="startTime" value="10"></c:set>
                        <c:forEach begin="1" end="3">    
                            <div class="row">
                                <c:forEach begin="0" end="2">
                                    <div class="col-lg-4 d-flex align-items-center">
                                        <input type="radio" id="${startTime}" name="booking-time" value="${startTime}:00" required>   
                                        <label for="${startTime}" class="booking-time">${startTime}:00</label>                              
                                    </div>
                                    <c:set var="startTime" value="${startTime+1}"></c:set>
                                </c:forEach>
                            </div>
                        </c:forEach>
                        <div class="mb-3">
                            <input type="submit" class="form-control btn-secondary" id="formGroupExampleInput" value="Booking" required>
                        </div>
                    </div>
                </form>
            </section>

            <section class="section-about" id="about">
                <h1 class="section-title">About</h1>
                <div class="row">
                    <div class="col-lg-6">
                        <div class="about-content" style="color:#fff">
                            <h3>What is Lorem Ipsum?</h3>
                            <p class="w-75 lh-sm fs-5">
                                Lorem Ipsum is simply dummy text of the printing and typesetting industry. 
                                Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, 
                                when an unknown printer took a galley of type and scrambled it to make a type specimen book. 
                                It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. 
                                It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with 
                                desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.</p>
                        </div>
                        <div class="about-social d-flex w-50 justify-content-evenly">
                            <a href="#" class="fs-1 text"><i class="fa-brands fa-facebook"></i></a>
                            <a href="#" class="fs-1 text"><i class="fa-brands fa-instagram"></i></a>
                            <a href="tel: +842367300999" class="fs-1 text"><i class="fa-solid fa-phone"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-6">
                        <div class="about-map">
                            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d3835.8639210998554!2d108.25836275068951!3d15.96848118888897!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x3142116949840599%3A0x365b35580f52e8d5!2zxJDhuqFpIGjhu41jIEZQVCDEkMOgIE7hurVuZyAoRlBUIHVuaXZlcnNpdHkgRGEgTmFuZyk!5e0!3m2!1svi!2s!4v1655440870007!5m2!1svi!2s"  height="450" style="border:0; width: 100%" allowfullscreen="" loading="lazy" referrerpolicy="no-referrer-when-downgrade"></iframe>
                        </div>
                    </div>
                </div>
            </section>
        </main>


        <jsp:include page="layout/footer.jsp"/>

    </body>
</html>
