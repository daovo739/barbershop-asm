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
        <jsp:include page="layout/navbarProducts.jsp"/>
        <main>
            <section class="container; border: none">
                <div class="row">
                    <form class="col-lg-2 filters" action="">
                        <div class="input-group mb-3  bg-dark text-white">
                            <input type="text" class="form-control" name="filter-search" placeholder="Search here!!" aria-label="Recipient's username" aria-describedby="basic-addon2" oninput="searchList(this.value)">
                        </div>
                        <div class="filter-category">
                            <h2 class="text-white">Category</h2>
                            <ul class="list-group">
                                <c:forEach items="${categories}" var="item">
                                    <a href="products?category=${item}" class="list-group-item list-group-item-action text-capitalize bg-dark text-white-50">${item}</a>
                                </c:forEach>
                            </ul>
                        </div>
                        <div class="filter-price">
                            <h2 class="text-white">Price</h2>
                            <label for="customRange2" class="form-label text-white" id="value-range"></label>
                            <input type="range" class="form-range" min="1" max="100" id="customRange2" oninput="showValue(this.value)">
                        </div>
                    </form>
                    <div class="col-lg-10">
                        <div class="row products-list gy-5">
                            <c:forEach items="${products}" var="product">
                                <div class="col-lg-3 d-flex align-items-stretch" style="width: 15rem">
                                    <a href="product?id=${product.getId()}" class="card bg-dark" style="width:100%; text-decoration: none;">
                                        <img src="${product.getImgLink()}" class="card-img-top img-thumbnail" alt="..." >
                                        <div class="card-body">
                                            <h6 class="card-subtitle mb-2 text-muted text-center text-capitalize" style="font-size: 10px">${product.getBrand()}</h6>
                                            <h5 class="card-title text-capitalize text-center text-white" style="font-size: 14px">${product.getName()}</h5>
                                            <h5 class="card-text text-capitalize text-center text-white-50" style="font-size: 20px">$${product.getPrice()}</h5>
                                        </div>
                                    </a>
                                </div> 
                            </c:forEach> 

                        </div>
                        <nav aria-label="Page navigation example " style="margin-top: 20px" class="d-flex justify-content-center">
                            <ul class="pagination ">
                                <li class="page-item">
                                    <a class="page-link bg-dark text-white" href="products?category=${category}&page=${prevPage}" aria-label="Previous">
                                        <span aria-hidden="true">&laquo;</span>
                                    </a>
                                </li>
                                <c:forEach begin="${1}" end="${totalPage}" var="i">
                                    <c:if test = "${i == pageCurrent}">
                                        <li class="page-item active"><a class="page-link bg-dark text-white" href="products?category=${category}&page=${i}">${i}</a></li>
                                        </c:if>
                                        <c:if test = "${i != pageCurrent}">
                                        <li class="page-item"><a class="page-link bg-dark text-white" href="products?category=${category}&page=${i}">${i}</a></li>
                                        </c:if>
                                    </c:forEach>
                                <a class="page-link bg-dark text-white" href="products?category=${category}&page=${nextPage}" aria-label="Next">
                                    <span aria-hidden="true">&raquo;</span>
                                </a>
                                </li>
                            </ul>
                        </nav>                                      
                    </div>
                </div>
            </section>
        </main>
        <jsp:include page="layout/footer.jsp"/>
    </body>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <script >
        const showValue = (value) => {
            document.getElementById("value-range").textContent = '$0 - $' + value;
            $.ajax({
                url: "/barbershop/search",
                type: "GET",
                cache: false,
                data: {
                    price: value
                },
                success: function (results) {
                    document.querySelector(".products-list").innerHTML = results;
                },
                error: function (error) {
                    console.log(error);
                }
            });
        };


        const searchList = (value) => {
            $.ajax({
                url: "/barbershop/search",
                type: "GET",
                cache: false,
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
        };
    </script>
</html>
