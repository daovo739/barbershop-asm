<%-- 
    Document   : productsAdmin
    Created on : Jun 25, 2022, 5:25:57 PM
    Author     : HHPC
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ADMIN HOME PAGE</title>
        <link type="text/css" rel="stylesheet" href="./static/css/style.css">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.1.1/css/all.min.css" integrity="sha512-KfkfwYDsLkIlwQp6LFnl8zNdLGxu9YAA1QvwINks4PhcElQSvqcyVLLD9aMhXd13uQjoXtEKNosOWaZqXgel0g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
        <style>
            /*            body::-webkit-scrollbar {
                            width: 0;
                        }
                        
                        products-container::-webkit-scrollbar{
                            width: 6px;
                        }
            
                        products-container::-webkit-scrollbar-track {
                            background: #f1f1f1;
                        }
            
                        products-container::-webkit-scrollbar-thumb {
                            background: #888;
                        }*/
        </style>
    </head>
    <body>
        <div class="wrapper d-flex align-items-stretch">
            <jsp:include page="/layout/admin/navbarAdmin.jsp"/>
            <div class="w-100" style="padding: 12px; margin-left: 280px;" >
                <h1 class="section-title bg-white text-dark"style="padding: 0; margin-bottom: 0">Products</h1>
                <section>
                    <div class="btn-container d-flex" >
                        <button class="btn btn-primary ">Add product</button>
                        <input type="text" id="id" value="" class="bg-dark text-white rounded ps-2 ms-2" placeholder="Search here!">
                    </div>
                    <hr>
                    <div class="d-flex flex-column products-container container" style="overflow-y: scroll; max-height: 500px">
                        <c:forEach var="product" items="${products}">
                            <div class="d-flex border-bottom mt-2 justify-content-between align-items-center">
                                <div class="d-flex align-items-center" style="width: 50%">
                                    <img src="${product.getImgLink()}" alt="alt" class="img-fluid" style="width: 150px; height: 150px"/>
                                    <div>
                                        <h5 class="text-capitalize" style="font-size: 20px">${product.getName()}</h5>
                                        <h6 class=" text-capitalize text-start" style="font-size: 14px">${product.getBrand()}</h6>
                                    </div>               
                                </div>
                                <h5 class="text-capitalize"  style="font-size: 26px">$${product.getPrice()}</h5>
                                <button class="btn btn-primary " style="padding: 12px; font-size: 16px" data-bs-toggle="modal" href="#exampleModalToggle${product.getId()}">Update</button>
                            </div>

                            <div class="modal fade " id="exampleModalToggle${product.getId()}" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                <div class="modal-dialog modal-dialog-centered ">
                                    <div class="modal-content ">
                                        <div class="modal-header">
                                            <h5 class="modal-title" id="exampleModalLabel">Update product <strong>#${product.getId()}</strong></h5>
                                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                        </div>
                                        <form class="modal-body">
                              
                                            <input type="text" id="name" name="name" placeholder="Enter name" class="form-control mb-2">
                                            <input type="text" id="brand" name="brand" placeholder="Enter brand" class="form-control mb-2">
                                            <input type="text" id="price" name="price" placeholder="Enter price" class="form-control mb-2">
                                            <div class="input-group mb-2">
                                                <label class="input-group-text" for="inputGroupFile01">Upload</label>
                                                <input type="file" id="ajaxfile" class="form-control">
                                            </div>

                                        </form>
                                        <div class="modal-footer">
                                            <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                                            <button data-id="${product.getId()}" type="button" class="btn btn-primary" onclick="updateProduct(this)">Update</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </div>
                </section>
            </div>
        </div>
            <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script> 
            <script>
                const updateProduct = (ele) => {
                    const id = ele.getAttribute("data-id");
                    const name = document.querySelector("#name").value || "";
                    const brand = document.querySelector("#brand").value || "";
                    const price = document.querySelector("#price").value || "";
                    const ajaxfile = document.querySelector("#ajaxfile");
                    
                    if (!name && !brand && !price){
                        console.log("empty");
                    }else{
                        let formData = new FormData();
                        formData.append("file", ajaxfile.files[0]);
                    $.ajax({
                        url: "/barbershop/productsAdmin",
                        type: "POST",
                        data: {
                            id: id,
                            name: name,
                            brand: brand,
                            price: price,
                            formData: formData
                        },
                        success: function (results) {
//                            document.querySelector(".cart-count").textContent = results;
                            console.log(results);
                        },
                        error: function (error) {
                            console.log(error);
                        }
                    });
                    }
                    
                };
            </script>

    </body>
</html>