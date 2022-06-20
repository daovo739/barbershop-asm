<%-- 
    Document   : product
    Created on : Jun 17, 2022, 11:44:01 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <jsp:include page="layout/navbar.jsp"/>
        <main style="min-height: calc(100vh - 126px - 125px)">
            <section class="container" >
                <c:if test="${not empty msg}">
                    <h1 class="text-center text-white" >${msg}</h1>
                </c:if>
                <c:if test="${empty msg}">
                    <div class="row">
                        <h2 class="text-start text-white mb-3" >Product / <span class="fst-italic fw-lighter text-capitalize">${product.getBrand()}</span> </h2>
                        <div class="col-lg-6">
                            <div>
                                <img src="${product.getImgLink()}" class="rounded img-fluid" alt="img-${product.getId()}" style="min-width: 80%">
                            </div>
                        </div>
                        <div class="col-lg-6 text-white  ">
                            <div class = "d-flex flex-column justify-content-start h-100">
                                <div>
                                    <h4 class="fst-italic text-capitalize" style="letter-spacing: 2px">${product.getName()}</h4>
                                    <h3>$${product.getPrice()}</h3>
                                </div>
                                <form action="" class="d-flex w-50 justify-content-between mt-3" >
                                    <input type="number" name="quantity" min="1" value="1" style="width: 50px; text-align: center">
                                    <input type="submit" class="form-control btn-secondary w-75 rounded-pill" id="formGroupExampleInput" value="Add To Cart" required="">
                                </form>
                                <div class="mt-3">
                                    <h2>Product Description</h2>
                                    <p class="w-75 lh-bas">Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged.</p>
                                </div>
                                <a class="btn btn-primary w-50" href="products?category=all" role="button">Continue Shopping &raquo;</a>
                             </div>
                        </div>
                    </div>
                </c:if>
            </section>
        </main>
        <jsp:include page="layout/footer.jsp"/>
    </body>
</html>
