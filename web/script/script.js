/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */


const addToCart = (product) => {
    const id = product.getAttribute('data-product-id');
    const quantityEle = document.querySelector("#quantity");
    quantity = !quantityEle ? 1 : quantityEle.value;
    $.ajax({
        url: "/barbershop/addToCart",
        type: "GET",
        data: {
            quantity: quantity,
            id: id
        },
        success: function (results) {
            document.querySelector(".cart-count").textContent = results;
//            console.log(results);
        },
        error: function (error) {
            console.log(error);
        }
    });
};

const  toastTrigger = document.querySelectorAll('.addCartBtn');
const toastLiveExample = document.getElementById('liveToast');
if (toastTrigger) {
    toastTrigger.forEach((item) => {
        item.addEventListener('click', function () {
            var toast = new bootstrap.Toast(toastLiveExample);
            toast.show();
        });
    });
};