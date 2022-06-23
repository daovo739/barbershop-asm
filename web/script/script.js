/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const formAddCart = document.querySelector(".form-add-to-cart");


//formAddCart.addEventListener("submit", () => {
//    const productId = document.querySelector("#productId");
//    const quantity = document.querySelector("#quantity");
//    $.ajax({
//        url: "/barbershop/addToCart",
//        type: "GET",
//        data: {
//            productId: productId,
//            quantity: quantity
//        },
//        success: function (results) {
//            console.log(2);
//            document.querySelector(".cart-list").innerHTML = results;
//        },
//        error: function (error) {
//            console.log(error);
//        }
//    });
//});

const getCart = () => {
    $.ajax({
        url: "/barbershop/GetCartServlet",
        type: "GET",
        success: function (results) {
            console.log(2);
            document.querySelector(".cart-list").innerHTML = results;
        },
        error: function (error) {
            console.log(error);
        }
    });
}