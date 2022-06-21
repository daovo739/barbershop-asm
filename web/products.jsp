<%-- 
    Document   : products.jsp
    Created on : Jun 17, 2022, 1:17:48 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Products</title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp"/>
        <main >
            <section class="container; border: none">
                <div class="row">
                    <div class="col-lg-2 filters" style="border-right: 1px solid #FCFDFB" >
                        <div class="input-group mb-3  bg-dark text-white">
                            <input type="text" class="form-control" name="filter-search" placeholder="Search here!!" aria-label="Recipient's username" aria-describedby="basic-addon2" oninput="searchList(this.value)">
                        </div>
                        <div class="filter-category">
                            <h2 class="text-white">Category</h2>
                            <ul class="list-group">
                                <c:forEach items="${categories}" var="item">
                                    <c:if test="${not empty sort}">
                                        <a href="products?category=${item}&sort=${sort}" class="list-group-item list-group-item-action text-capitalize bg-dark text-white-50">${item}</a>
                                    </c:if>
                                    <c:if test="${empty sort}">
                                        <a href="products?category=${item}" class="list-group-item list-group-item-action text-capitalize bg-dark text-white-50">${item}</a>
                                    </c:if>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="filter-price d-flex flex-column">
                            <h2 class="text-white">Price</h2>
                            <a class="btn btn-secondary " href="products?category=${category}&sort=asc" role="button">Ascending</a>
                            <a class="btn btn-secondary mt-2" href="products?category=${category}&sort=desc" role="button">Descending</a>
                        </div>
                        <c:if test="${not empty filtering}">
                            <div class="fiter-fitering d-flex flex-column mt-3">
                                <h2 class="text-white">Filtering</h2>
                                <c:forEach items="${filtering}" var="filter">
                                    <p class="text-white text-capitalize">- ${filter}</p>
                                </c:forEach>
                            </div>
                        </c:if>

                        <hr style="background: #fff"/>
                        <a class="btn btn-danger mt-2 w-100 text-uppercase" href="products" role="button">Unfiltered</a>
                    </div>
                    <div class="col-lg-10">
                        <c:if test="${empty msg}">
                            <div class="row products-list gy-5">
                            <c:forEach items="${products}" var="product">
                                <div class="col-lg-3 d-flex align-items-stretch" style="width: 15rem">
                                    <a href="product?id=${product.getId()}" class="card bg-dark" style="width:100%; text-decoration: none;">
                                        <div class="card-view-container">
                                            <img src="${product.getImgLink()}" class="card-img-top img-thumbnail" alt="..." >  
                                             <div class="card-view">View product</div>    
                                        </div>                                         
                                        <div class="card-body d-flex flex-column justify-content-between">
                                            <h6 class="card-subtitle mb-2 text-muted text-center text-capitalize" style="font-size: 10px">${product.getBrand()}</h6>
                                            <h5 class="card-title text-capitalize text-center text-white" style="font-size: 14px">${product.getName()}</h5>
                                            <h5 class="card-text text-capitalize text-center text-white-50" style="font-size: 20px">$${product.getPrice()}</h5>
                                        </div>
                                        <form action="addToCart?" class="d-flex justify-content-center">
                                            <input type="text" value="${product.getId()}" name="id" hidden="true"/>
                                            <input  type="submit" class="btn btn-primary mb-3 text-white w-100" style="max-width: 75%" value="Add to cart"/>
                                        </form>
                                    </a>
                                </div> 
                            </c:forEach> 
                        </div>
                        </c:if>
                        <h1 class="text-white text-center">${msg}</h1>
                        <c:if test="${not empty msg}">
                            
                        </c:if>
                        <!-------------Pagination---------------->
                        <jsp:include page="layout/pagination.jsp"/>                                
                    </div>
                </div>
            </section>
        </main>
        <jsp:include page="layout/footer.jsp"/>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script >
        const searchList = (value) => {
            const pagination = document.querySelector("#pagination-container");
            if (value) {
                $.ajax({
                    url: "/barbershop/search",
                    type: "GET",
                    data: {
                        search: value
                    },
                    success: function (results) {
                        document.querySelector(".products-list").innerHTML = results;
                    },
                    error: function (error) {
                        console.log(error);
                    }
                });
                pagination.classList.add('d-none');
            } else {
                pagination.classList.remove('d-none');
            }

        };
    </script>
</html>
