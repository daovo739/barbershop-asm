/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */
    const toastLiveExample = document.getElementById('liveToastAdmin');
    const toast = new bootstrap.Toast(toastLiveExample);
    
const updateProduct = async (ele) => {
    const id = ele.getAttribute("data-id");
    const name = document.querySelector("#name-" + id).value || "";
    const brand = document.querySelector("#brand-" + id).value || "";
    const price = document.querySelector("#price-" + id).value || "";
    const ajaxfile = document.querySelector("#ajaxfile-" + id);
    const file = ajaxfile.files[0];

    if (!name && !brand && !price && !file) {
        console.log("empty");
    } else {
        let data = new FormData();
        data.append("file", file);
        data.append("id", id);
        data.append("name", name);
        data.append("brand", brand);
        data.append("price", price);
        $.ajax({
            url: "/barbershop/productsAdmin",
            type: "POST",
            processData: false,
            contentType: false,
            enctype: 'multipart/form-data',
            data: data,
            success: function (results) {
//                document.querySelector(".close-modal").classList.add("toastTrigger");
                document.querySelector(".close-modal").click();
                document.querySelector(".products-container").innerHTML = results;
                document.querySelector(".toast-body-admin").textContent = "Update successfully!";            
                toast.show();
//                            console.log(results);
            },
            error: function (error) {
                document.querySelector(".toast-body-admin").textContent = "Update failed!";
                toast.show();
                console.log(error);
            }
        });
    }

};
