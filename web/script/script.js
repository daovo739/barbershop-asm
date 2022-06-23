/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const addToCart = (value) => {
//    const id = document.querySelector("#id").value;
    const quantity = document.querySelector("#quantity").value;
    if (!quantity) {
        quantity = 1;
    }
    $.ajax({
        url: "/barbershop/addToCart",
        type: "GET",
        data: {
            quantity: quantity,
            id: value
        },
        success: function (results) {
            console.log(results);
        },
        error: function (error) {
            console.log(error);
        }
    });
};